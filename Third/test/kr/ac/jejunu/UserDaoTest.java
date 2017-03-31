package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by super on 2017-03-15.
 */
public class UserDaoTest {
    UserDao userDao;

    @Before
    public void setup() throws Exception{
        ApplicationContext context = new GenericXmlApplicationContext("setting.xml");
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void getUser() throws Exception{
        long id  = 1;
        String name = "woochan";
        String password = "1234";

        User user = userDao.getUser(id);
        assertThat(id , is(user.getId()));
        assertThat(name , is(user.getName()));
        assertThat(password , is(user.getPassword()));
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
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void updateUser()throws Exception{
        String name = "우찬";
        String password = "aasdf";

        String newName = "Woochan";
        String newPassword = "kim";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.addUser(user);

        user.setId(id);
        user.setName(newName);
        user.setPassword(newPassword);

        userDao.updateUser(user);

        User changedUser = userDao.getUser(id);

        assertThat(changedUser.getId(), is(id));
        assertThat(changedUser.getName(), is(newName));
        assertThat(changedUser.getPassword(), is(newPassword));
    }

    @Test
    public void deleteUser() throws Exception{
        String name = "우찬";
        String password = "aasdf";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.addUser(user);

        userDao.deleteUser(id);

        User deleteuser = userDao.getUser(id);
        assertThat(null, is(deleteuser));
    }
}
