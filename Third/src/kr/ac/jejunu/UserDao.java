package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private JdbcTemplate context ;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        String sql = "select id, name, password from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        User user1 = null;
        try {
            user1 = context.queryForObject(sql, parms, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            });
        } catch (org.springframework.dao.DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
        return user1;
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (name, password) values(?, ?)";
        Object[] parms = new Object[] {user.getName(), user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        context.update((connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            return statement;
        }),keyHolder);
        return (Long) keyHolder.getKey();
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "update userinfo set name=?, password=? where id = ?";
        Object[] parms = new Object[]{user.getName(), user.getPassword(), user.getId()};
        context.update(sql, parms);
    }

    public void deleteUser(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] parms = new Object[]{id};
        context.update(sql, parms);
    }


    public void setContext(JdbcTemplate context) {
        this.context = context;
    }
}
