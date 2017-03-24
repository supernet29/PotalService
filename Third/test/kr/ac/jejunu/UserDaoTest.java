package kr.ac.jejunu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {
    UserDao userDao;
    @Before
    public void setup() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void getUserName() throws Exception{
        long id  = 1;
        String name = "woochan";
        String password = "1234";

        DaoFactory daoFactory = new DaoFactory();

        User user = userDao.getUser(id);
        Assert.assertThat(id , is(user.getId()));
        Assert.assertThat(name , is(user.getName()));
        Assert.assertThat(password , is(user.getPassword()));
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
        Assert.assertThat(name, is(user.getName()));
        Assert.assertThat(password, is(user.getPassword()));
    }

    /*
    @Test
    public void getHallaUserName() throws Exception{
        UserDao userDao = new UserDao(new HallaConnectionMaker());
        long id  = 1;
        String name = "woochan";
        String password = "1234";

        User user = userDao.getUser(id);
        Assert.assertThat(id , is(user.getId()));
        Assert.assertThat(name , is(user.getName()));
        Assert.assertThat(password , is(user.getPassword()));
    }

    @Test
    public void addHallaUser() throws Exception {
        String name = "우찬";
        String password = "aasdf";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new UserDao(new HallaConnectionMaker());
        Long id = userDao.addUser(user);
        user = userDao.getUser(id);
        Assert.assertThat(name, is(user.getName()));
        Assert.assertThat(password, is(user.getPassword()));
    }
    */
}
