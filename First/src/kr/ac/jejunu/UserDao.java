package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private DataSource dataSource;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        PreparedStatementStrategy statementStrategy = new GetUserPrepareStatementStrategy(id);
        User user = null;
        ResultSet result = null;
        PreparedStatement query = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            query = statementStrategy.getPreparedStatement(connection);
            result = query.executeQuery();
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
        PreparedStatementStrategy preparedStatementStrategy = new AddUserPrepareStatementStrategy(user);
        Connection connection = null;
        PreparedStatement query = null;
        try {
            connection = dataSource.getConnection();
            query = preparedStatementStrategy.getPreparedStatement(connection);
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

    public void deleteUser(long id) throws SQLException {
        PreparedStatementStrategy preparedStatementStrategy = new DeleteUserPrepareStatementStrategy(id);
        Connection connection = dataSource.getConnection();
        PreparedStatement query = null;
        try {
            query = preparedStatementStrategy.getPreparedStatement(connection);
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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
