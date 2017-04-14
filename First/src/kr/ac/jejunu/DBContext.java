package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-14.
 */
public class DBContext {

    private DataSource dataSource;

    public User selectQuery(PreparedStatementStrategy statementStrategy) throws SQLException {
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
            if (result != null)
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return  user;
    }

    public void UpdateQuery(PreparedStatementStrategy preparedStatementStrategy) throws SQLException {
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

    public PreparedStatementStrategy makePrepareStatement(String sql, Object[] parms) {
        return connection -> {
            PreparedStatement query = connection.prepareStatement(sql);
            for(int i = 1; i <= parms.length; i++) {
                query.setObject(i, parms[i-1]);
            }
            return query;
        };
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
