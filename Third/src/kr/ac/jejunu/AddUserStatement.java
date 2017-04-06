package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public class AddUserStatement implements PrepareStatementStrategy{
    @Override
    public PreparedStatement makePrepareStatement(Connection connection, Object object) throws SQLException {
        User user = (User) object;
        PreparedStatement query = connection.prepareStatement("insert into userinfo (name, password) values(?, ?)");
        query.setString(1, user.getName());
        query.setString(2, user.getPassword());
        return query;
    }
}
