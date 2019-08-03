package com.pig.learn.mybatis.threadlocal;

import com.google.common.collect.Maps;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class MyContext {

//    private static ApplicationContext applicationContext;


    private Map<String,Object> map = Maps.newHashMap();

//    public static void setApplicationContext(ApplicationContext applicationContext){
//        MyContext.applicationContext = applicationContext;
//    }
//
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public MyContext() {
    }

    public MyContext(Map<String, Object> map) {
        this.map = map;
    }
}
