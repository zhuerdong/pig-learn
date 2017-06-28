package com.pig.learn.mybatis.controller;

import com.google.common.collect.Lists;
import com.pig.learn.mybatis.Excel.ExportExcel;
import com.pig.learn.mybatis.model.TestExcelModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @Author: pig
 * @Date: Created in 下午5:28 17/6/28
 * @Description:
 */
@RequestMapping("/test")
@Controller
public class ExcelController {

    @RequestMapping("/excel/export")
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response) {

        ExportExcel<TestExcelModel> exportExcel = new ExportExcel(request, response, "qws");

        TestExcelModel one = new TestExcelModel(1, "erdong");
        TestExcelModel two = new TestExcelModel(2, "tianyu");

        List<String> titleNameList = Lists.newArrayList("id", "name");

        List<TestExcelModel> objectList = Lists.newArrayList(one, two);

        List<String> fieldNameList = Lists.newArrayList("id", "name");

        exportExcel.addAll(titleNameList, objectList, fieldNameList);

        return "OK";
    }
}
