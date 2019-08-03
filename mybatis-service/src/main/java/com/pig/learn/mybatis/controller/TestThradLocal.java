package com.pig.learn.mybatis.controller;

import com.pig.learn.mybatis.domain.User;
import com.pig.learn.mybatis.gson.GsonUtil;
import com.pig.learn.mybatis.threadlocal.MyContext;
import com.pig.learn.mybatis.threadlocal.ServiceContextManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/thread")
public class TestThradLocal {

    static int a=100;
    static {
        a=101;

    }
    @RequestMapping("/check")
    @ResponseBody
    public String check() {
        try{

            MyContext context = ServiceContextManager.getMyContext();

            //Integer b = ServiceContextManager.getMyContext();

            System.out.println(a);

            Map<String, Object> map = context.getMap();


            map.put("1","22222");



        }catch (Exception e){
            System.out.println("1111111");
        }finally {


            MyContext context1 =ServiceContextManager.getMyContext();
            ServiceContextManager.clean();


            MyContext context2 =ServiceContextManager.getMyContext();

            System.out.println(111);
        }

        return "ok";
    }

}
