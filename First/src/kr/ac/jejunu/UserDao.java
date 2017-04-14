package kr.ac.jejunu;

import javax.sql.ConnectionPoolDataSource;
import java.sql.*;
import java.util.Objects;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        Connection connection = getConnection();
        PreparedStatement query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        query.setLong(1, id);
        ResultSet result = query.executeQuery();
        result.next();

        User user = new User();
        user.setId(result.getLong("id"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));
        return user;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://117.17.102.106/woochan?characterEncoding=utf-8", "root", "1234");
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        Connection connection = getConnection();
        PreparedStatement query = connection.prepareStatement("insert into userinfo (id, name, password) values(?, ?, ?)");
        query.setLong(1, user.getId());
        query.setString(2, user.getName());
        query.setString(3, user.getPassword());
        query.executeUpdate();

        query.close();
        connection.close();
    }
}
