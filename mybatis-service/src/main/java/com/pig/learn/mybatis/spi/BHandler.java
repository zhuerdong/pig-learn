package com.pig.learn.mybatis.spi;

import com.pig.learn.mybatis.spi.spiRouterByAnnoation.SpiAnnotation;
import org.springframework.stereotype.Component;

@Component
@SpiAnnotation("bhander")
public class BHandler extends Handler{
    private static final String NAME = "bhander";

    @Override
    public void process() {
        System.out.println("BHandler.process");
    }

    @Override
    public String getHandleName() {
        return NAME;
    }
}
