package com.pig.learn.mybatis.controller;

import com.pig.learn.mybatis.domain.User;
import com.pig.learn.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author daimao  Date: 15/9/30 Time: 下午12:20
 * @version $Id$
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/check")
    @ResponseBody
    public String check() {
        User user = userMapper.selectByPrimaryKey(1);

        return "OK";
    }
}