package kr.ac.jejunu;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by super on 2017-05-12.
 */
public class AnnotationConfigApplicationContextTest {
    @Test
    public void applicationContext(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu");
        Hello hello = applicationContext.getBean("helloPerson",Hello.class);
        System.out.println(hello.sayHello());
    }
}
