package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private DataSource connectionMaker;

    public UserDao(DataSource connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    //TODO make selectQuery() and refactor
    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        User user = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet result = null;
        PrepareStatementStrategy statementStrategy = new GetUserStatement(id);
        try {
            connection = connectionMaker.getConnection();
            query = statementStrategy.makePrepareStatement(connection);
            result = query.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            throw e;
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

    //TODO make selectQuery() and refactor
    public Long getLastInsertId(Connection connection) throws SQLException {
        long id = 0;
        PrepareStatementStrategy statementStrategy = new GetLastInsertInsertStatement();
        ResultSet resultSet = null;
        PreparedStatement query = null;
        try {
            query = statementStrategy.makePrepareStatement(connection);
            resultSet = query.executeQuery();
            if(resultSet.next())
                id = resultSet.getLong(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

    //TODO make selectQuery()
    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        Connection connection = null;
        PrepareStatementStrategy statementStrategy = new AddUserStatement(user);
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy);

            long id = getLastInsertId(connection);
            return id;
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PrepareStatementStrategy statementStrategy = new UpdateUserStatement(user);
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void deleteUser(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PrepareStatementStrategy statementStrategy = new DeleteUserStatement(id);
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private void updateQuery(Connection connection, PrepareStatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        PreparedStatement query = null;
        try {
            query = statementStrategy.makePrepareStatement(connection);
            query.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
