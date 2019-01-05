package com.pig.learn.mybatis.spi;

import com.pig.learn.mybatis.spi.spiRouterByAnnoation.SpiAnnotation;
import org.springframework.stereotype.Component;

@Component
@SpiAnnotation("chander")
public class CHandler extends Handler{

    private static final String NAME = "chander";

    @Override
    public void process() {
        System.out.println("CHandler.process");
    }

    @Override
    public String getHandleName() {
        return NAME;
    }


}
