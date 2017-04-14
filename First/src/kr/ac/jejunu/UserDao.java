package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private DBContext dbContext;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        String sql = "select id, name, password from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        PreparedStatementStrategy statementStrategy = dbContext.makePrepareStatement(sql, parms);
        return dbContext.selectQuery(statementStrategy);
    }


    public void addUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (id, name, password) values(?, ?, ?)";
        Object[] parms = new Object[] { user.getId(), user.getName(), user.getPassword()};
        PreparedStatementStrategy preparedStatementStrategy = dbContext.makePrepareStatement(sql, parms);
        dbContext.UpdateQuery(preparedStatementStrategy);
    }


    public void deleteUser(long id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        PreparedStatementStrategy preparedStatementStrategy = dbContext.makePrepareStatement(sql, parms);
        dbContext.UpdateQuery(preparedStatementStrategy);
    }

    public void setDbContext(DBContext dbContext) {
        this.dbContext = dbContext;
    }
}
