package com.pig.learn.mybatis.gson;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pig.learn.mybatis.domain.User;

import java.util.List;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * @Author: zhuerdong
 * @Date: Created in 上午10:22 17/6/29
 * @Description:
 */

public class GsonUtil {
    public static final Gson gson = new GsonBuilder().serializeNulls().create();


    public static void main(String[] args) {
        listToJson();
    }

    private static void listToJson() {
        List<User> users = Lists.newArrayList();
        User user = new User();
        user.setId(1);
        user.setName("zhuerdong");

        int i = 3;
        for (int i1 = 0; i1 < i; i1++) {
            users.add(user);
        }

        String json = gson.toJson(users);

        System.out.println(json);

        List<User> users1 = gson.fromJson(json,new TypeToken<List<User>>(){}.getType());
        System.out.println(users1);
    }

}
