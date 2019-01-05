package com.pig.learn.mybatis.spi;

import com.pig.learn.mybatis.spi.spiRouterByAnnoation.SpiAnnotation;
import org.springframework.stereotype.Component;

import javax.xml.ws.handler.MessageContext;

@Component
@SpiAnnotation("ahander")
public class AHandler extends Handler{
    private static final String NAME = "ahander";

    @Override
    public void process() {
        System.out.println("AHandler.process");
    }

    @Override
    public String getHandleName() {
        return "ahander";
    }
}
