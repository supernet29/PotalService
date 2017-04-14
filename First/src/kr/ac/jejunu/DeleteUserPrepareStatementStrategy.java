package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-14.
 */
public class DeleteUserPrepareStatementStrategy implements PreparedStatementStrategy{
    private long id;

    public DeleteUserPrepareStatementStrategy(long id) {
       this.id = id;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement query = connection.prepareStatement("delete from userinfo where id = ?");
        query.setLong(1, id);
        return query;
    }
}
