package com.pig.learn.mybatis.threadlocal;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceContextManager {


    private static  ThreadLocal<MyContext>  contextHolder;


    static{
        contextHolder  = new ThreadLocal<MyContext>();

        contextHolder.set(new MyContext());


    }




    public static MyContext getMyContext() {
        return contextHolder.get();
    }

    public static  void clean(){
        contextHolder.remove();
    }
}
