package com.pig.learn.mybatis.controller;

import com.pig.learn.mybatis.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHello {

      @Log
     @ResponseBody
      @RequestMapping(value="/Log", method = RequestMethod.GET)
      public String sayHello(){
               System.out.println("------现在调用真正的sayhello方法-----");
                return "sayHello";
            }

}
