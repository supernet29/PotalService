package kr.ac.jejunu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.lang.model.type.NullType;
import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {

    UserDao userDao;

    @Before
    public void before() throws Exception{
        ApplicationContext context = new GenericXmlApplicationContext("setting.xml");
        userDao =  context.getBean("UserDao", UserDao.class);
    }

    @Test
    public void getUser() throws Exception{
        long id  = 1;
        String name = "woochan";
        String password = "1234";

        User user = userDao.getUser(id);
        Assert.assertThat(id , is(user.getId()));
        Assert.assertThat(name , is(user.getName()));
        Assert.assertThat(password , is(user.getPassword()));
    }

    @Test
    public void addUser() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        long id = random.nextLong();
        String name = "우찬";
        String password = "aasdf";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.addUser(user);

        user = userDao.getUser(id);
        Assert.assertThat(user.getId(), is(id));
        Assert.assertThat(name, is(user.getName()));
        Assert.assertThat(password, is(user.getPassword()));
    }

    @Test
    public void deleteUser() throws Exception{
        Random random = new Random(System.currentTimeMillis());
        long id = random.nextLong();
        String name = "우찬";
        String password = "aasdf";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.addUser(user);
        userDao.deleteUser(id);
        user =  userDao.getUser(id);
        Assert.assertNull(user);
    }
}
