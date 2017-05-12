package kr.ac.jejunu.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by super on 2017-05-12.
 */
public class HelloFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(HelloFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("filter");
    }

    @Override
    public void destroy() {
        logger.info("end");
    }
}
