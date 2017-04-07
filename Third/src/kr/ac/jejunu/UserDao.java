package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private DBContext context;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        PrepareStatementStrategy statementStrategy = connection -> {
            PreparedStatement query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            query.setLong(1, id);
            return query;
        };
        return context.getQuery(statementStrategy);
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        PrepareStatementStrategy statementStrategy = connection -> {
            PreparedStatement query = connection.prepareStatement("insert into userinfo (name, password) values(?, ?)");
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            return query;
        };
        return context.addQuery(statementStrategy);
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        PrepareStatementStrategy statementStrategy = connection -> {
            PreparedStatement query = connection.prepareStatement("update userinfo set name=?, password=? where id = ?");
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.setLong(3, user.getId());
            return query;
        };
        context.updateQuery(statementStrategy);
    }

    public void deleteUser(Long id) throws SQLException, ClassNotFoundException {
        PrepareStatementStrategy statementStrategy = connection -> {
            PreparedStatement query = connection.prepareStatement("delete from userinfo where id = ?");
            query.setLong(1, id);
            return query;
        };
        context.updateQuery(statementStrategy);
    }

    public void setContext(DBContext context) {
        this.context = context;
    }
}
