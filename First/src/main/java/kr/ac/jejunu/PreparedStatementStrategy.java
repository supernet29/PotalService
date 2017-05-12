package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-14.
 */
public interface PreparedStatementStrategy {
    PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}
