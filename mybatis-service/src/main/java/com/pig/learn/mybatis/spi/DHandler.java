package com.pig.learn.mybatis.spi;

import com.pig.learn.mybatis.spi.spiRouterByAnnoation.SpiAnnotation;
import org.springframework.stereotype.Component;

@Component
@SpiAnnotation("dhander")
public class DHandler extends Handler{
    private static final String NAME = "dhander";

    @Override
    public void process() {
        System.out.println("DHandler.process");
    }

    @Override
    public String getHandleName() {
        return NAME;
    }
}
