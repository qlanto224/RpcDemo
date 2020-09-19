package com.ql.learn.tomcat;

import com.ql.learn.pojo.Invocation;
import com.ql.learn.pojo.URL;
import com.ql.learn.registry.NativeRegistry;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author qinlei
 * @description 处理远程调用请求
 * @date 2020/9/18 17:41
 */
public class HttpServerHandler {
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        //服务请求处理逻辑
        try {

            //1.通过请求流获取请求服务调用的参数
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) objectInputStream.readObject();

            //2.从注册中心获取服务的列表
            Class implClass = NativeRegistry.get(new URL("localhost",8899),invocation.getInterfaceName());
            //3.调用服务-映射
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(),invocation.getParams());
            //4.返回结果
            IOUtils.write(result,resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
