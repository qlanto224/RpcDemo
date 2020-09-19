package com.ql.learn.consumer;


import com.ql.learn.pojo.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author qinlei
 * @description 发起远程调用
 * @date 2020/9/19 9:02
 */
public class HttpClient {

    public String post(String hostname, int port , Invocation invocation){
        try {
            //进行连接
            URL url = new URL("http",hostname,port,"/client");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //发送调用信息
            OutputStream ops = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ops);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            //将输入流转为字符串(这里可以是java对象) 获取远程调用的结果
            InputStream ins = connection.getInputStream();
           return IOUtils.toString(ins,"UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
