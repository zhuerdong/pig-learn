package com.pig.learn.mybatis.spi.spiRouterByInterfaceSimple;

import com.pig.learn.mybatis.spi.Handler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author pig  Date: 15/9/30 Time: 下午12:20
 * @version $Id$
 */
@Controller
@RequestMapping("/test")
public class SpiControllerSimple {


    @RequestMapping("/Spi")
    @ResponseBody
    public void check( @RequestParam("handlerType") String handlerType) {

        Handler handler = SpiRouterSimple.getSpi(handlerType);

        System.out.println(handler.getHandleName());
        handler.process();

    }
}