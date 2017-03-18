package kr.ac.jejunu;

import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {

    private static Connection connection;
    private static UserDao userDao;

    @BeforeClass
    public static void init() throws Exception{
        connection = getConnection();
        userDao = new UserDao(connection);
    }

    @Test
    public void getUserName() throws Exception{
        long id  = 1;
        String name = "woochan";
        String password = "1234";

        User user = userDao.getUser(id);
        Assert.assertThat(user.getId() , is(id));
        Assert.assertThat(user.getName() , is(name));
        Assert.assertThat(user.getPassword() , is(password));
    }

    @Test
    public void addUser() throws Exception {
        String name = "우찬";
        String password = "aasdf";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.addUser(user);

        user = userDao.getUser(id);
        Assert.assertThat(user.getName(), is(name));
        Assert.assertThat(user.getPassword(), is(password));
    }

    @AfterClass
    public static void quit() throws Exception{
        userDao.close();
        connection.close();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/woochan?characterEncoding=utf-8", "jeju", "1234");
    }
}
