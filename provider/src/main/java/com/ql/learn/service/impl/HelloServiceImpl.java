package com.ql.learn.service.impl;

import com.ql.learn.service.HelloService;

/**
 * @author qinlei
 * @description todo
 * @date 2020/9/18 16:47
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String message) {
        return message+"调用了服务提供方";
    }
}
