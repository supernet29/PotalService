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
        PreparedStatement query = connection.prepareStatement("select id, name, password from jeju where id = ?");
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
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=utf-8", "jeju", "1234");
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        Connection connection = getConnection();
        PreparedStatement query = connection.prepareStatement("insert into jeju (name, password) values(?, ?)");
        query.setString(1, user.getName());
        query.setString(2, user.getPassword());
        query.executeUpdate();
        query.close();

        query = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = query.executeQuery();
        resultSet.next();

        long id = resultSet.getLong(1);
        resultSet.close();
        query.close();
        connection.close();
        return id;
    }
}
