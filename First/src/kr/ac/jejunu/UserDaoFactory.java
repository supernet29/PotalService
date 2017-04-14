package kr.ac.jejunu;

/**
 * Created by super on 2017-04-14.
 */
public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDao(getConnectionMaker());
    }

    public ConnectionMaker getConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
