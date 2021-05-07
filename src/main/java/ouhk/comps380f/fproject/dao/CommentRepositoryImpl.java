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
                    comment.setUserId(rs.getLong("user_id"));
                    comment.setItemId(rs.getLong("item_id"));
                    comment.setContent(rs.getString("comment_content"));
                    comment.setDate(rs.getDate("comment_date"));
                    comment.setUsername(rs.getString("username"));
                    map.put(id, comment);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long createComment(final Long userId, final long itemId, final String comment, final Date date) {
        // TODO Auto-generated method stub
        final String SQL_INSERT_COMMENT = "INSERT INTO item_comments (user_id, item_id, comment_content, comment_date) VALUES (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_COMMENT, new String[]{"comment_id"});
                ps.setLong(1, userId);
                ps.setLong(2, itemId);
                ps.setString(3, comment);
                ps.setDate(4, (java.sql.Date) date);
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
        final String SQL_SELECT_COMMENTS = "SELECT c.*, u.username FROM item_comments AS c, users AS u WHERE item_id = ? AND c.user_id = u.user_id";
        return jdbcOp.query(SQL_SELECT_COMMENTS, new CommentExtractor(), itemId);
    }

    private static final class CommentRowMapper implements RowMapper<Comments>{

        @Override
        public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            Comments comment = new Comments();
            comment.setItemId(rs.getLong("item_id"));
            comment.setId(rs.getLong("comment_id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setDate(rs.getDate("comment_date"));
            comment.setContent(rs.getString("comment_content"));
            comment.setUsername(rs.getString("username"));
            return comment;
        }
    }

    @Override
    @Transactional
    public Comments getComment(long id) {
        // TODO Auto-generated method stub
        //TODO: change the SQL to query for username as well please
        final String SQL_SELECT_COMMENT = "SELECT c.item_id, c.comment_id, c.user_id, c.comment_date, c.comment_content, u.username FROM item_comments AS c, users AS u WHERE comment_id = ? AND c.user_id = u.user_id";
        return (Comments) jdbcOp.query(SQL_SELECT_COMMENT, new CommentRowMapper(), id);
    }

    @Override
    @Transactional
    public void updateComment(long id, String comment, Date date) {
        // TODO Auto-generated method stub
        final String SQL_UPDATE_COMMENT = "UPDATE item_comments SET comment_content = ?, comment_date = ? WHERE comment_id = ?";
        jdbcOp.update(SQL_UPDATE_COMMENT, comment, (java.sql.Date) date, id);
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