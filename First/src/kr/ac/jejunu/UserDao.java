package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        String sql = "select id, name, password from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, parms, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (id, name, password) values(?, ?, ?)";
        Object[] parms = new Object[] { user.getId(), user.getName(), user.getPassword()};
        jdbcTemplate.update(sql, parms);
    }

    public void deleteUser(long id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        jdbcTemplate.update(sql, parms);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
