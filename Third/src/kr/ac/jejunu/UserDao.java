package kr.ac.jejunu;

import java.sql.*;
import java.util.Objects;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    //TODO make selectQuery() and refactor
    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        User user = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet result = null;
        PrepareStatementStrategy statementStrategy = new GetUserStatement();
        try {
            connection = connectionMaker.getConnection();
            query = statementStrategy.makePrepareStatement(connection, id);
            result = query.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            throw e;
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
            query = statementStrategy.makePrepareStatement(connection, id);
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
        PrepareStatementStrategy statementStrategy = new AddUserStatement();
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy, user);

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
        PrepareStatementStrategy statementStrategy = new UpdateUserStatement();
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy, user);
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
        PrepareStatementStrategy statementStrategy = new DeleteUserStatement();
        try {
            connection = connectionMaker.getConnection();
            updateQuery(connection, statementStrategy, id);
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

    private void updateQuery(Connection connection, PrepareStatementStrategy statementStrategy, Object object) throws ClassNotFoundException, SQLException {
        PreparedStatement query = null;
        try {
            query = statementStrategy.makePrepareStatement(connection, object);
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
