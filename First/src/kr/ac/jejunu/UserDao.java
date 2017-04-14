package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private DataSource connectionMaker;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();
        User user = null;
        ResultSet result = null;
        PreparedStatement query = null;
        try {
            query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            query.setLong(1, id);
            result = query.executeQuery();
            user = null;
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
            }
        } finally {
            if(result != null)
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }


    public void addUser(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement query = null;
        try {
            query = connection.prepareStatement("insert into userinfo (id, name, password) values(?, ?, ?)");
            query.setLong(1, user.getId());
            query.setString(2, user.getName());
            query.setString(3, user.getPassword());
            query.executeUpdate();
        } finally {
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(query != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void setConnectionMaker(DataSource connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
