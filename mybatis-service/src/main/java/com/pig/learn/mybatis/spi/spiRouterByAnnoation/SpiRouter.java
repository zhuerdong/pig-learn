package com.pig.learn.mybatis.spi.spiRouterByAnnoation;


import com.google.common.collect.Maps;
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
public class SpiRouter implements ApplicationContextAware,InitializingBean{

    private static ApplicationContext applicationContext;

    private static ConcurrentHashMap<Class<?>,Map<String,Object>> cache = new ConcurrentHashMap<Class<?>,Map<String,Object>>();


    @Override
    public void afterPropertiesSet() throws Exception {
        //主动加载
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //扫描注解的方式,每一种接口对应一个map集合，可以做到重复多次的使用
    public static <T> T getSpi(Class<T> inface,String type){
        Map<String,Object> spis = cache.get(inface);
        if(spis != null){
            return  (T) spis.get(type);
        }

        spis = Maps.newHashMap();
        Map<String,T> map = applicationContext.getBeansOfType(inface);

        if(MapUtils.isEmpty(map)){
            //cache.put(inface,spis);
            return null;
        }

        for (Map.Entry<String, T> entry : map.entrySet()) {
            SpiAnnotation spiAnnotation = entry.getValue().getClass().getAnnotation(SpiAnnotation.class);
            if(spiAnnotation != null){
                spis.put(spiAnnotation.value(),entry.getValue());
            }
        }

        cache.put(inface,spis);

        return (T)spis.get(type);



    }
}
