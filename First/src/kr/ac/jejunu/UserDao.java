package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {
    private DBContext dbContext;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        PreparedStatementStrategy statementStrategy = new GetUserPrepareStatementStrategy(id);
        return dbContext.selectQuery(statementStrategy);
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        PreparedStatementStrategy preparedStatementStrategy = new AddUserPrepareStatementStrategy(user);
        dbContext.UpdateQuery(preparedStatementStrategy);
    }


    public void deleteUser(long id) throws SQLException {
        PreparedStatementStrategy preparedStatementStrategy = new DeleteUserPrepareStatementStrategy(id);
        dbContext.UpdateQuery(preparedStatementStrategy);
    }

    public void setDbContext(DBContext dbContext) {
        this.dbContext = dbContext;
    }
}
