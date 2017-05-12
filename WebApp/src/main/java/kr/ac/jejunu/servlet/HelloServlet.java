package kr.ac.jejunu.servlet;

import kr.ac.jejunu.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by super on 2017-05-12.
 */
@WebServlet("/hello")
public class HelloServlet extends GenericServlet{
    @Autowired
    @Qualifier("helloPerson")
    private Hello hello;

    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println(hello.sayHello());
        res.flushBuffer();
        logger.info("servlet");
    }

    @Override
    public void destroy() {
        logger.info("end");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        logger.info("start");
        super.init();
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }
}

