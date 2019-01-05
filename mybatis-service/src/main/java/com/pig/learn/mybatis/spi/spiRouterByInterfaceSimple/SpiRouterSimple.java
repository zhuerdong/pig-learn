package com.pig.learn.mybatis.spi.spiRouterByInterfaceSimple;


import com.google.common.collect.Maps;
import com.pig.learn.mybatis.spi.Handler;
import com.pig.learn.mybatis.spi.spiRouterByAnnoation.SpiAnnotation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SpiRouterSimple implements ApplicationContextAware,InitializingBean{

    private static ApplicationContext applicationContext;

    private static Map<String,Handler> handlerMap = Maps.newHashMap();

    private static  Map<String,Handler> map = Maps.newHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
         map = applicationContext.getBeansOfType(Handler.class);
        //主动加载

        for (String key : map.keySet()) {
            Handler value = map.get(key);
            handlerMap.put(value.getHandleName(),value);
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //扫描注解的方式,每一种接口对应一个map集合，可以做到重复多次的使用
    public static Handler getSpi(String type){

        return handlerMap.get(type);



    }
}
