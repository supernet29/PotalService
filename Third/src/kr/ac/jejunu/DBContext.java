package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-07.
 */
public class DBContext {

    private DataSource connectionMaker;

    public User getQuery(PrepareStatementStrategy statementStrategy) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet result = null;
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

    private Long getLastInsertId(Connection connection) throws SQLException {
        long id = 0;
        ResultSet resultSet = null;
        PreparedStatement query = null;
        try {
            query = connection.prepareStatement("select last_insert_id()");
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


    public Long addQuery(PrepareStatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement query;
        try {
            connection = connectionMaker.getConnection();
            query = statementStrategy.makePrepareStatement(connection);
            query.executeUpdate();
            long id = getLastInsertId(connection);
            return id;
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


    public void updateQuery(PrepareStatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        PreparedStatement query = null;
        Connection connection = null;
        try {
            connection = connectionMaker.getConnection();
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
            if(connection != null)
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
