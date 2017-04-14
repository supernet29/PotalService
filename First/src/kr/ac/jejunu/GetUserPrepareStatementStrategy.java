package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-14.
 */
public class GetUserPrepareStatementStrategy implements PreparedStatementStrategy{
    long id;
    public GetUserPrepareStatementStrategy(long id){
        this.id = id;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement query;
        query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        query.setLong(1, id);
        return query;
    }
}
