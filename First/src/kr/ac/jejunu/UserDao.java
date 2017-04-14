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


    public void addUser(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement query = connection.prepareStatement("insert into userinfo (id, name, password) values(?, ?, ?)");
        query.setLong(1, user.getId());
        query.setString(2, user.getName());
        query.setString(3, user.getPassword());
        query.executeUpdate();

        query.close();
        connection.close();
    }

    public void setConnectionMaker(DataSource connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
