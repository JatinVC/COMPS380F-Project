/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ouhk.comps380f.fproject.model.Comments;

/**
 *
 * @author Jatin
 */

@Repository
public class CommentRepositoryImpl implements CommentRepository{

    private final JdbcOperations jdbcOp;

    @Autowired
    public CommentRepositoryImpl(DataSource datasource){
        jdbcOp = new JdbcTemplate(datasource);
    }

    private final static class CommentExtractor implements ResultSetExtractor<List<Comments>>{

        @Override
        public List<Comments> extractData(ResultSet rs) throws SQLException, DataAccessException {
            // TODO Auto-generated method stub
            Map<Long, Comments> map = new HashMap<>();
            while(rs.next()){
                Long id = rs.getLong("comment_id");
                Comments comment = map.get(id);
                if(comment == null){
                    comment = new Comments();
                    comment.setId(id);
                    comment.setItemId(rs.getLong("item_id"));
                    comment.setContent(rs.getString("comment_content"));
                    LocalDate date = LocalDate.parse(rs.getString("comment_date"));
                    comment.setDate(date);
                    map.put(id, comment);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long createComment(final long itemId, final String comment) {
        // TODO Auto-generated method stub
        final String SQL_INSERT_COMMENT = "INSERT INTO item_comments (item_id, comment_content, comment_date) VALUES (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_COMMENT, new String[]{"comment_id"});
                ps.setLong(1, itemId);
                ps.setString(2, comment);
                LocalDate date = LocalDate.now();
                ps.setString(3, date.toString());
                return ps;
            }
        }, keyHolder);

        Long commentId = keyHolder.getKey().longValue();
        System.out.println("Comment " + commentId + " inserted");
        
        return commentId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comments> getComments(long itemId) {
        // TODO Auto-generated method stub
        final String SQL_SELECT_COMMENTS = "SELECT *FROM item_comments WHERE item_id = ?";
        return jdbcOp.query(SQL_SELECT_COMMENTS, new CommentExtractor(), itemId);
    }

    private static final class CommentRowMapper implements RowMapper<Comments>{

        @Override
        public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            Comments comment = new Comments();
            comment.setItemId(rs.getLong("item_id"));
            comment.setId(rs.getLong("comment_id"));
            LocalDate date = LocalDate.parse(rs.getString("comment_date"));
            comment.setDate(date);
            comment.setContent(rs.getString("comment_content"));
            return comment;
        }
    }

    @Override
    @Transactional
    public Comments getComment(long id) {
        // TODO Auto-generated method stub
        final String SQL_SELECT_COMMENT = "SELECT item_id, comment_id, comment_date, comment_content FROM item_comments WHERE comment_id = ?";
        return (Comments) jdbcOp.query(SQL_SELECT_COMMENT, new CommentRowMapper(), id);
    }

    @Override
    @Transactional
    public void updateComment(long id, String comment) {
        // TODO Auto-generated method stub
        final String SQL_UPDATE_COMMENT = "UPDATE item_comments SET comment_content = ?, comment_date = ? WHERE comment_id = ?";
        LocalDate date = LocalDate.now();
        jdbcOp.update(SQL_UPDATE_COMMENT, comment, date.toString(), id);
        System.out.println("Comment " + id + " udpated");        
    }

    @Override
    public void deleteComment(long id) {
        // TODO Auto-generated method stub
        final String SQL_DELETE_COMMENT = "DELETE FROM item_comments WHERE comment_id = ?";
        jdbcOp.update(SQL_DELETE_COMMENT, id);
        System.out.println("Comment " + id + " deleted");
    }
}