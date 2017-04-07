package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public class GetLastInsertInsertStatement implements PrepareStatementStrategy{
    @Override
    public PreparedStatement makePrepareStatement(Connection connection) throws SQLException {
        PreparedStatement query = connection.prepareStatement("select last_insert_id()");
        return query;
    }
}
