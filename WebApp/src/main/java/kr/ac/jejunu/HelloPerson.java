package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by super on 2017-05-12.
 */
@Component("helloPerson")
public class HelloPerson implements Hello {
    @Value("woochan")
    private String name;
    @Autowired
    private Hello hello;

    @Override
    public String sayHello() {
        return hello.sayHello() + " " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHello(HelloImpl hello) {
        this.hello = hello;
    }
}
