package com.pig.learn.mybatis.date;

import java.text.SimpleDateFormat;
import java.util.Date;

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
 * @Date: Created in 下午2:03 17/6/28
 * @Description:
 */

public class DateUtil {
    private static final String YMD_FORMAT_ONE = "yyyy-MM-dd";
    //这种可以继续转换成数字啊
    private static final String YMD_FORMAT_TWO = "yyyyMMdd";

    private static final String YMD_HMS_FORMAT="yyyy-MM-dd HH:mm:ss";

    /**
     * 将Date转为年月日格式的String
     * @param date
     * @return
     */
    public static String getDateFormatYmd(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS_FORMAT);
            return sdf.format(date);
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        //2017-05-20 14:02:03
        System.out.println(getDateFormatYmd(new Date()));
    }
}
