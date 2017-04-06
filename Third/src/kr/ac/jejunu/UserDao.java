package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by super on 2017-03-15.
 */
public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public User getUser(long id) throws SQLException, ClassNotFoundException {
        // database is in mysql
        User user = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            connection = connectionMaker.getConnection();
            query = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            query.setLong(1, id);
            result = query.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(result != null)
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }

    public Long addUser(User user) throws ClassNotFoundException, SQLException {
        // database is in mysql
        ResultSet resultSet = null;
        PreparedStatement query = null;
        Connection connection = null;
        try {
            connection = connectionMaker.getConnection();
            query = connection.prepareStatement("insert into userinfo (name, password) values(?, ?)");
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.executeUpdate();
            query.close();

            query = connection.prepareStatement("select last_insert_id()");
            resultSet = query.executeQuery();
            resultSet.next();
            long id = resultSet.getLong(1);
            return id;
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement query = null;
        Connection connection = null;
        try {
            connection = connectionMaker.getConnection();
            query = connection.prepareStatement("update userinfo set name=?, password=? where id = ?");
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.setLong(3, user.getId());
            query.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void deleteUser(Long id) throws SQLException, ClassNotFoundException {
        PreparedStatement query = null;
        Connection connection = null;
        try {
            connection = connectionMaker.getConnection();
            query = connection.prepareStatement("delete from userinfo where id = ?");
            query.setLong(1, id);
            query.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(query != null)
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
