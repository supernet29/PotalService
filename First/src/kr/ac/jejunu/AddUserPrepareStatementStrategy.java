package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-14.
 */
public class AddUserPrepareStatementStrategy implements PreparedStatementStrategy{
    private User user;

    public AddUserPrepareStatementStrategy(User user){
        this.user = user;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement query = connection.prepareStatement("insert into userinfo (id, name, password) values(?, ?, ?)");
        query.setLong(1, user.getId());
        query.setString(2, user.getName());
        query.setString(3, user.getPassword());
        return query;
    }
}
