package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public class GetUserStatement implements PrepareStatementStrategy{
    private Long id ;
    public GetUserStatement(Long id){
        this.id = id;
    }
    @Override
    public PreparedStatement makePrepareStatement(Connection connection) throws SQLException {
        PreparedStatement query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        query.setLong(1, id);
        return query;
    }
}
