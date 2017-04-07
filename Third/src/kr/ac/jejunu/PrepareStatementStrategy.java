package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by kimwoochan on 2017-04-06.
 */
public interface PrepareStatementStrategy {
    PreparedStatement makePrepareStatement(Connection connection) throws SQLException;
}
