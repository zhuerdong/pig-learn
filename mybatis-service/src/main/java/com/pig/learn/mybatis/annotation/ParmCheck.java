package com.pig.learn.mybatis.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParmCheck {

    //需要校验的参数,登录时肯定需要，平常不要，所以default false
    boolean userId() default false;

    //需要校验的参数，任何时候都需要
    boolean userName() default true;


}
