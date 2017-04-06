package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public class UpdateUserStatement implements PrepareStatementStrategy{
    @Override
    public PreparedStatement makePrepareStatement(Connection connection, Object object) throws SQLException {
        User user = (User) object;
        PreparedStatement query = connection.prepareStatement("update userinfo set name=?, password=? where id = ?");
        query.setString(1, user.getName());
        query.setString(2, user.getPassword());
        query.setLong(3, user.getId());
        return query;
    }
}
