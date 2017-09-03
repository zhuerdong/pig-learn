package com.pig.learn.mybatis.csv;

import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvWriter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: pig
 * @Date: Created in 下午4:03 16/12/16
 * @Description:
 */
@Controller
public class CSVController {


    @RequestMapping(value = "/csv/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response){
        try {
            boolean result = exportSelection(response);
            if (!result) {
                returnFail(response);
            }
        } catch (Exception ie) {
            returnFail(response);
        }
    }

    /**
     * 返回失败
     * @param response 响应
     */
    private void returnFail(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSONObject.toJSONString("error"));
        } catch (Exception e) {
        }
    }

    public boolean exportSelection(HttpServletResponse response) throws IOException {
        response.setContentType("application/x-excel;charset=GBK");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + "test" + "_" + new Date()+ ".csv");
        return CsvUtil.writeCsv(response.getOutputStream(), new CsvUtil.Write() {
            @Override
            public boolean writeCsv(CsvWriter wr) {
                String[] row = new String[15];
                List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
                if (CollectionUtils.isEmpty(list)) {
                    return false;
                }
                writeHeader(wr,row);
                for (Integer i : list) {
                    try {
                        row[0] = i.toString();
                        row[1] = i.toString();
                        row[2] = i.toString();
                        row[3] = i.toString();
                        row[4] = i.toString();
                        row[5] = i.toString();
                        row[6] = i.toString();
                        row[7] = i.toString();
                        row[8] = i.toString();
                        row[9] = i.toString();
                        row[10] = i.toString();
                        row[11] = i.toString();
                        row[12] = i.toString();
                        row[13] = i.toString();
                        row[14] = i.toString();

                        wr.writeRecord(row);
                    } catch (IOException e) {
                    }
                }
                return true;
            }

        });
    }

    private void writeHeader(CsvWriter wr,String[] row){
        try{
            row[0] = "A";
            row[1] = "B";
            row[2] = "C";
            row[3] = "D";
            row[4] = "E";
            row[5] = "F";
            row[6] = "G";
            row[7] = "E";
            row[8] = "F";
            row[9] = "H";
            row[10] = "K";
            row[11] = "I";
            row[12] = "M";
            row[13] = "N";
            row[14] = "R";
            wr.writeRecord(row);
        }catch (Exception e){

        }

    }
}
