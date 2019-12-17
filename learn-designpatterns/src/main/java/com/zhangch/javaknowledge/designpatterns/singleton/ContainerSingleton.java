package com.zhangch.javaknowledge.designpatterns.singleton;


import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by geely
 */
public class ContainerSingleton {

    private ContainerSingleton(){

    }
    private static Map<String,Object> singletonMap = new HashMap<String,Object>();

    public static void putInstance(String key,Object instance){
        if(StrUtil.isNotBlank(key) && instance != null){
            if(!singletonMap.containsKey(key)){
                singletonMap.put(key,instance);
            }
        }
    }

    public static Object getInstance(String key){
        return singletonMap.get(key);
    }


}
