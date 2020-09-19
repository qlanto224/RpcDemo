package com.ql.learn.tomcat;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @author qinlei
 * @description 构建一个tomcat
 * @date 2020/9/18 17:24
 */
public class HttpServer {

    public void start(String hostname,Integer port){
        //实例化tomcat
        Tomcat tomcat = new Tomcat();

        //创建server
        Server server = tomcat.getServer();

        //获取service
        Service service = server.findService("Tomcat");

        //构建connector
        Connector connector = new Connector();
        connector.setPort(port);
        connector.setURIEncoding("UTF-8");

        //构建engine
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        //构建host
        Host host = new StandardHost();
        host.setName(hostname);

        //构建context
        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        //tomcat的生命周期监听器
        context.addLifecycleListener(new Tomcat.FixContextListener());

        //一层一层将子节点添加到父节点
        host.addChild(context);
        engine.addChild(host);
        service.setContainer(engine);
        service.addConnector(connector);//这里的service本来就是从server中拿到的

        //tomcat是一个servlet,设置路径和映射
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();//启动tomcat
            tomcat.getServer().await();//接受请求
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
