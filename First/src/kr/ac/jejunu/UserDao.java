package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao implements AutoCloseable{
    private Connection connection;
    private PreparedStatement getUserQuery;
    private PreparedStatement addUserQuery;
    private PreparedStatement getLastAddIDQuery;

    public UserDao(Connection connection) throws SQLException {
        this.connection = connection;
        addUserQuery = connection.prepareStatement("insert into userinfo (name, password) values(?, ?)");
        getUserQuery = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        getLastAddIDQuery = connection.prepareStatement("select last_insert_id()");
    }

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        getUserQuery.setLong(1, id);
        ResultSet resultSet = getUserQuery.executeQuery();
        resultSet.next();

        User user = new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("password")
        );

        resultSet.close();
        return user;
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        addUserQuery.setString(1, user.getName());
        addUserQuery.setString(2, user.getPassword());
        addUserQuery.executeUpdate();

        ResultSet resultSet = getLastAddIDQuery.executeQuery();
        resultSet.next();

        long id = resultSet.getLong(1);
        resultSet.close();

        return id;
    }

    @Override
    public void close() throws Exception {
        addUserQuery.close();
        getUserQuery.close();
        getLastAddIDQuery.close();
    }
}
