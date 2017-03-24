package kr.ac.jejunu;

/**
 * Created by super on 2017-03-24.
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}