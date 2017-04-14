package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by super on 2017-04-14.
 */
@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao UserDao(){
        return new UserDao(getConnectionMaker());
    }
    @Bean
    public ConnectionMaker getConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
