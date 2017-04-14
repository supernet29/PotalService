package kr.ac.jejunu;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {
    @Test
    public void getUserName() throws Exception{
        UserDao userDao = new UserDao(new JejuConnectionMaker());
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

        UserDao userDao = new UserDao(new JejuConnectionMaker());
        userDao.addUser(user);

        user = userDao.getUser(id);
        Assert.assertThat(user.getId(), is(id));
        Assert.assertThat(name, is(user.getName()));
        Assert.assertThat(password, is(user.getPassword()));
    }
}
