package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        Connection connection = connectionMaker.getConnection();
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

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        Connection connection = connectionMaker.getConnection();
        PreparedStatement query = connection.prepareStatement("insert into userinfo (name, password) values(?, ?)");
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