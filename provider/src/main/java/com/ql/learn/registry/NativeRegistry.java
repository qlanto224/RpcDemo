package com.ql.learn.registry;

import com.ql.learn.pojo.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinlei
 * @description 本地注册中心
 * @date 2020/9/18 17:12
 */
public class NativeRegistry {
    //注册中心
    private static Map<String, Map<URL,Class>> registryCenter = new HashMap<String, Map<URL,Class>>();

    /**
     * 注册服务
     */
    public static void registry(String interfaceName,URL url,Class implClass){
        Map<URL, Class> hashMap = new HashMap<>();
        hashMap.put(url,implClass);
        registryCenter.put(interfaceName,hashMap);
    }

    /**
     * 从注册中心获取服务
     * @param url   请求的路径(ip port)
     * @param interfaceName 接口名称
     * @return
     */
    public static Class get(URL url,String interfaceName){
        return registryCenter.get(interfaceName).get(url);
    }
}
