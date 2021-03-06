/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ouhk.comps380f.fproject.model.FoodItem;

/**
 *
 * @author Jatin
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcOperations jdbcOp;

    @Autowired
    public ItemRepositoryImpl(DataSource datasource) {
        jdbcOp = new JdbcTemplate(datasource);
    }

    private static final class ItemExtractor implements ResultSetExtractor<List<FoodItem>> {

        @Override
        public List<FoodItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
            // TODO Auto-generated method stub
            Map<Long, FoodItem> map = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("item_id");
                FoodItem item = map.get(id);
                if (item == null) {
                    item = new FoodItem();
                    item.setId(id);
                    item.setFoodName(rs.getString("item_name"));
                    item.setPrice(rs.getInt("item_price"));
                    item.setDescription(rs.getString("item_description"));
                    item.setQuantity(rs.getBoolean("item_availability"));
                    map.put(id, item);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    @Override
    @Transactional
    public long createItem(final String itemName, final int price, final String description, final Boolean availability) throws IOException {
        // TODO Auto-generated method stub
        final String SQL_INSERT_ITEM = "INSERT INTO items (item_name, item_price, item_description, item_availability) values (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOp.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ITEM, new String[] { "item_id" });
                ps.setString(1, itemName);
                ps.setInt(2, price);
                ps.setString(3, description);
                ps.setBoolean(4, true);
                return ps;
            }
        }, keyHolder);

        Long itemId = keyHolder.getKey().longValue();
        System.out.println("Item " + Long.toString(itemId) + " inserted");

        return itemId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodItem> getItems() {
        // TODO Auto-generated method stub
        final String SQL_SELECT_ITEMS = "select * from items";
        return jdbcOp.query(SQL_SELECT_ITEMS, new ItemExtractor());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodItem> getItem(long id) {
        // TODO Auto-generated method stub
        final String SQL_SELECT_ITEM = "select * from items where item_id = ?";
        return jdbcOp.query(SQL_SELECT_ITEM, new ItemExtractor(), id);
    }

    @Override
    @Transactional
    public void updateItem(long itemId, String itemName, int price, String description, Boolean availability) throws IOException {
        // TODO Auto-generated method stub
        final String SQL_UPDATE_ITEM = "UPDATE items SET item_name=?, item_price=?, item_description=?, item_availability=? WHERE item_id=?";
        jdbcOp.update(SQL_UPDATE_ITEM, itemName, price, description, availability, itemId);
        System.out.println("Item " + itemId + " updated");
    }

    @Override
    @Transactional
    public void deleteItem(long itemId) {
        // TODO Auto-generated method stub
        final String SQL_DELETE_ITEM = "DELETE FROM items WHERE item_id = ?";
        //no cascade delete so need to delete everything in the 3 tables related here
        final String SQL_DELETE_ATTACHMENT = "DELETE FROM item_picture WHERE item_id = ?";
        final String SQL_DELETE_COMMENTS = "DELETE FROM item_comments WHERE item_id = ?";
        jdbcOp.update(SQL_DELETE_ATTACHMENT, itemId);
        jdbcOp.update(SQL_DELETE_COMMENTS, itemId);
        jdbcOp.update(SQL_DELETE_ITEM, itemId);
        System.out.println("Item " + itemId + " deleted");
    }
    @Override
    @Transactional
    public void setAvaToFalse(long itemId) {
        final String SQL_UPDATE_ITEM = "UPDATE items SET item_availability=false WHERE item_id=?";
        jdbcOp.update(SQL_UPDATE_ITEM, itemId);
    }
    @Override
    @Transactional
    public void setAvaToTrue(long itemId) {
        final String SQL_UPDATE_ITEM = "UPDATE items SET item_availability=true WHERE item_id=?";
        jdbcOp.update(SQL_UPDATE_ITEM, itemId);
    }
}
