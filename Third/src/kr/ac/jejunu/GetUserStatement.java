package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public class GetUserStatement implements PrepareStatementStrategy{
    @Override
    public PreparedStatement makePrepareStatement(Connection connection, Object object) throws SQLException {
        Long id = (Long)object;
        PreparedStatement query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        query.setLong(1, id);
        return query;
    }
}