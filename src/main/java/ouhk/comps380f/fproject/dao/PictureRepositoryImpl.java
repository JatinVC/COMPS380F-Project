/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ouhk.comps380f.fproject.model.Attachment;

/**
 *
 * @author Jatin
 */
@Repository
public class PictureRepositoryImpl implements PictureRepository{

    private final JdbcOperations jdbcOp;

    @Autowired
    public PictureRepositoryImpl(DataSource dataSource){
        jdbcOp = new JdbcTemplate(dataSource);
    }

    private static final class AttachmentExtractor implements ResultSetExtractor<List<Attachment>>{

        @Override
        public List<Attachment> extractData(ResultSet rs) throws SQLException, DataAccessException {
            // TODO Auto-generated method stub
            Map<Long, Attachment> map = new HashMap<>();
            while(rs.next()){
                Long id = rs.getLong("picture_id");
                Attachment attachment = map.get(id);
                if(attachment == null){
                    attachment = new Attachment();
                    attachment.setId(id);
                    attachment.setItemId(rs.getLong("item_id"));
                    attachment.setAttachmentName(rs.getString("picture_name"));
                    attachment.setMimeContentType(rs.getString("picture_mimetype"));
                    Blob blob = rs.getBlob("picture_data");
                    byte[] data = blob.getBytes(1l, (int) blob.length());
                    attachment.setContents(data);
                    map.put(id, attachment);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    @Override
    @Transactional
    public long createPicture(final String pictureName, final String mimetype,final long itemId, final InputStream blob) {
        // TODO Auto-generated method stub
        final String SQL_INSERT_ATTACHMENT = "INSERT INTO item_picture(item_id, picture_name, picture_mimetype, picture_data) VALUES (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ATTACHMENT, new String[]{"picture_id"});
                ps.setLong(1, itemId);
                ps.setString(2, pictureName);
                ps.setString(3, mimetype);
                ps.setBlob(4, blob);
                return ps;            
            }
        }, keyHolder);

        Long pictureId = keyHolder.getKey().longValue();
        System.out.println("Attachment " + Long.toString(pictureId) + " inserted");

        return pictureId;
    }

    private static final class AttachmentRowMapper implements RowMapper<Attachment> {
        @Override
        public Attachment mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            Attachment entry = new Attachment();
            entry.setAttachmentName(rs.getString("picture_name"));
            entry.setMimeContentType(rs.getString("picture_mimetype"));
            Blob blob = rs.getBlob("picture_data");
            byte[] bytes = blob.getBytes(1l, (int) blob.length());
            entry.setContents(bytes);
            entry.setItemId(rs.getLong("item_id"));
            return entry;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attachment> getAttachments(long itemId) {
        // TODO Auto-generated method stub
        final String SQL_SELECT_ATTACHMENTS = "select * from item_picture where item_id = ?";
        return jdbcOp.query(SQL_SELECT_ATTACHMENTS, new AttachmentExtractor(), itemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attachment> getAttachment(long id, long itemId) {
        // TODO Auto-generated method stub
        final String SQL_SELECT_ATTACHMENT = "select * from item_picture where picture_id = ? and item_id = ?";
        return jdbcOp.query(SQL_SELECT_ATTACHMENT, new AttachmentRowMapper(), id, itemId);
    }

    @Override
    @Transactional
    public void updateAttachment(long itemId, long id, String pictureName, String mimetype, InputStream blob) {
        // TODO Auto-generated method stub
        final String SQL_UPDATE_ATTACHMENT = "UPDATE item_picture SET itemId = ?, picture_name = ?, picture_mimetype = ?, picture_data = ? where picture_id = ?";
        jdbcOp.update(SQL_UPDATE_ATTACHMENT, itemId, pictureName, mimetype, blob, id);  
        System.out.println("Attachment " + id + " updated");      
    }

    @Override
    @Transactional
    public void deleteAttachment(long itemId, long id) {
        // TODO Auto-generated method stub
        final String SQL_DELETE_ATTACHMENT = "DELETE FROM item_picture WHERE item_id = ? and picture_id = ?";
        jdbcOp.update(SQL_DELETE_ATTACHMENT, itemId, id);
        System.out.println("Attachment " + id + " deleted");
    }
}