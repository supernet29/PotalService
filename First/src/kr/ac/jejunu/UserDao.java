package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private DBContext dbContext;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        PreparedStatementStrategy statementStrategy = new PreparedStatementStrategy() {
            @Override
            public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement query;
                query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
                query.setLong(1, id);
                return query;
            }
        };
        return dbContext.selectQuery(statementStrategy);
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        PreparedStatementStrategy preparedStatementStrategy = new PreparedStatementStrategy() {
            @Override
            public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement query = connection.prepareStatement("insert into userinfo (id, name, password) values(?, ?, ?)");
                query.setLong(1, user.getId());
                query.setString(2, user.getName());
                query.setString(3, user.getPassword());
                return query;
            }
        };
        dbContext.UpdateQuery(preparedStatementStrategy);
    }


    public void deleteUser(long id) throws SQLException {
        PreparedStatementStrategy preparedStatementStrategy = new PreparedStatementStrategy() {
            @Override
            public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement query = connection.prepareStatement("delete from userinfo where id = ?");
                query.setLong(1, id);
                return query;
            }
        };
        dbContext.UpdateQuery(preparedStatementStrategy);
    }

    public void setDbContext(DBContext dbContext) {
        this.dbContext = dbContext;
    }
}
