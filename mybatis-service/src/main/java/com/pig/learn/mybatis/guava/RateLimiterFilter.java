package com.pig.learn.mybatis.guava;

import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 16.  * 　　　┏┓　　　┏┓
 * 17.  * 　　┏┛┻━━━┛┻┓
 * 18.  * 　　┃　　　　　　　┃
 * 19.  * 　　┃　　　━　　　┃
 * 20.  * 　　┃　┳┛　┗┳　┃
 * 21.  * 　　┃　　　　　　　┃
 * 22.  * 　　┃　　　┻　　　┃
 * 23.  * 　　┃　　　　　　　┃
 * 24.  * 　　┗━┓　　　┏━┛
 * 25.  * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 26.  * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 27.  * 　　　　┃　　　┗━━━┓
 * 28.  * 　　　　┃　　　　　　　┣┓
 * 29.  * 　　　　┃　　　　　　　┏┛
 * 30.  * 　　　　┗┓┓┏━┳┓┏┛
 * 31.  * 　　　　　┃┫┫　┃┫┫
 * 32.  * 　　　　　┗┻┛　┗┻┛
 * 33.  *
 * 34.  * @Author: zhuerdong
 * 35.  * @Date: Created in 下午9:47 17/2/9
 * 36.  * @Description:
 * 37.
 */

public class RateLimiterFilter implements Filter {

    private RateLimiter limiter = null;

    public void init(FilterConfig config) throws ServletException {
        try {
            //1000次访问每秒
            limiter = RateLimiter.create(1000);
        } catch (Exception e) {

        }

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (limiter.tryAcquire()) {
            //请求通过
            chain.doFilter(request, response);
        } else {
            //请求被限制，打到提示页面
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, res);
        }
    }
}

//然后在web.xml中配置拦截器，使他成为一个bean，并且规定哪些请求经过这个bean的拦截
//        [java] view plain copy
//        1. <filter>
//  2.         <filter-name>ratelimiter</filter-name>
//        3.         <filter-class>RateLimiterFilter</filter-class>
//        4.     </filter>
//        5.     <filter-mapping>
//                 <filter-name>ratelimiter</filter-name>
//                 <url-pattern>/ab/c/*</url-pattern>
//  8.     </filter-mapping>
//
//最后别忘记引入guava的jar包
