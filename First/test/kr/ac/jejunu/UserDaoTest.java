package kr.ac.jejunu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {

    UserDao userDao;

    @Before
    public void before() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        userDao =  context.getBean("UserDao", UserDao.class);

    }

    @Test
    public void getUserName() throws Exception{
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
}
