package com.pig.learn.mybatis.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Log {

    String name() default "";
}
