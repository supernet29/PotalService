package kr.ac.jejunu;

import org.springframework.stereotype.Component;

/**
 * Created by super on 2017-05-12.
 */
@Component("helloImpl")
public class HelloImpl implements Hello {
    @Override
    public String sayHello(){
        return "Hello";
    }
}
