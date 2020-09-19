package com.ql.learn.provider;

import com.ql.learn.pojo.URL;
import com.ql.learn.registry.NativeRegistry;
import com.ql.learn.service.HelloService;
import com.ql.learn.service.impl.HelloServiceImpl;
import com.ql.learn.tomcat.HttpServer;

/**
 * @author qinlei
 * @description todo
 * @date 2020/9/18 17:19
 */
public class ServiceProvider {
    public static void main(String[] args) {
        //真正的注册服务
        URL url = new URL("localhost",8899);
        NativeRegistry.registry(HelloService.class.getName(),url,HelloServiceImpl.class);

        //启动tomcat,暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
    }
}
