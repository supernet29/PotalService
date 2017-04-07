package kr.ac.jejunu;

import java.sql.*;
import java.util.Objects;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private DBContext context;

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        String sql = "select id, name, password from userinfo where id = ?";
        Object[] parms = new Object[] {id};
        PrepareStatementStrategy statementStrategy = context.prepareStatementParmameterSetting(sql, parms);
        return context.getQuery(statementStrategy);
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (name, password) values(?, ?)";
        Object[] parms = new Object[] {user.getName(), user.getPassword()};
        PrepareStatementStrategy statementStrategy = context.prepareStatementParmameterSetting(sql, parms);
        return context.addQuery(statementStrategy);
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "update userinfo set name=?, password=? where id = ?";
        Object[] parms = new Object[]{user.getName(), user.getPassword(), user.getId()};
        PrepareStatementStrategy statementStrategy = context.prepareStatementParmameterSetting(sql, parms);
        context.updateQuery(statementStrategy);
    }

    public void deleteUser(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] parms = new Object[]{id};
        PrepareStatementStrategy statementStrategy = context.prepareStatementParmameterSetting(sql, parms);
        context.updateQuery(statementStrategy);
    }


    public void setContext(DBContext context) {
        this.context = context;
    }
}
