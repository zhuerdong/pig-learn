package com.pig.learn.mybatis.base;

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
 * @Author: pig
 * @Date: Created in 上午10:30 17/6/29
 * @Description:基础类
 */

public interface BaseEnum {
    /**
     * 枚举的唯一标识
     *
     * @return
     */
    int getCode();

    /**
     * 枚举的描述信息
     *
     * @return
     */
    String getDesc();

    /**
     * 枚举的extra信息
     *
     * @return
     */
    String getExtra();

    /**
     * 枚举的字面值
     * @return
     */
    String getName();
}
