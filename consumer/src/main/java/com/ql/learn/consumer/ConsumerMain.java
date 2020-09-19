package com.ql.learn.consumer;

import com.ql.learn.pojo.Invocation;
import com.ql.learn.service.HelloService;

/**
 * @author qinlei
 * @description todo
 * @date 2020/9/19 9:32
 */
public class ConsumerMain {
    public static void main(String[] args) {
        Object[] params = new Object[1];
        params[0] = "rpcDemo客户端";
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", params, new Class[]{String.class});
        HttpClient httpClient = new HttpClient();
        String post = httpClient.post("localhost", 8899, invocation);
        System.out.println(post);
    }
}
