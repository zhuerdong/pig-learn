package com.pig.learn.mybatis.spi.spiRouterByAnnoation;

import com.pig.learn.mybatis.domain.User;
import com.pig.learn.mybatis.gson.GsonUtil;
import com.pig.learn.mybatis.mapper.UserMapper;
import com.pig.learn.mybatis.spi.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author pig  Date: 15/9/30 Time: 下午12:20
 * @version $Id$
 */
@Controller
@RequestMapping("/test")
public class SpiAnnoationController {


    @RequestMapping("/SpiAnnotation")
    @ResponseBody
    public void check( @RequestParam("handlerType") String handlerType) {

        Handler handler = SpiRouter.getSpi(Handler.class,handlerType);

        System.out.println(handler.getHandleName());
        handler.process();

    }
}