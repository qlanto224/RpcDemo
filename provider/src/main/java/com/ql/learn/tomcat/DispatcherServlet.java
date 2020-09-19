package com.ql.learn.tomcat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qinlei
 * @description todo
 * @date 2020/9/18 17:38
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        //处理远程调用请求
        new HttpServerHandler().handle(req,resp);
    }
}
