package com.pig.learn.mybatis.Excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExportExcel<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcel.class);

    private OutputStream outputStream;

    public ExportExcel(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            outputStream = response.getOutputStream();
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("rv:11.0")) {
                response.addHeader("Content-Disposition",
                        "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()) + ".xls\"");
            } else {
                response.addHeader("Content-Disposition", "attachment; filename=\""
                        + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + ".xls\"");
            }
            response.addHeader("Content-Type", "application/vnd.ms-excel");
        } catch (IOException e) {
            LOGGER.error("下载文件出错-初始化异常!", e);
        }
    }

    public void addAll(List<String> titleNameList, List<T> objectList, List<String> objectFieldNameList) {
        //参数检测
        if (null == objectList || CollectionUtils.isEmpty(titleNameList) || CollectionUtils.isEmpty(objectFieldNameList)) {
            throw new IllegalArgumentException("传入参数为空");
        }
        //objectList为size为0时
        if (titleNameList.size() != objectFieldNameList.size()) {
            throw new IllegalArgumentException("标题数与对应属性数不相等");
        }
        for (String fieldName : objectFieldNameList) {
            if (null == fieldName) {
                throw new IllegalArgumentException("属性名不能为空");
            }
        }
        //存储对象列表大象限制
        if (objectList.size() > 65534) {
            throw new IllegalArgumentException("对象列表过长，最多为65534个");
        }
        //初始化excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("表1");

        //设置title
        HSSFRow hssfRow = hssfSheet.createRow(0);

        for (int i = 0; i < titleNameList.size(); i++) {
            String titleName = titleNameList.get(i);
            if (null == titleName) {
                continue;
            }
            hssfRow.createCell(i).setCellValue(titleName);
        }

        int fieldNumber = objectFieldNameList.size();
        int countRow = 1;
        //填充内容
        for (T object : objectList) {
            if (null == object) {
                continue;
            }
            hssfRow = hssfSheet.createRow(countRow);
            for (int i = 0; i < fieldNumber; i++) {
                HSSFCell hssfCell = hssfRow.createCell(i);
                setCellValue(hssfCell, objectFieldNameList.get(i), object);
            }
            countRow++;
        }

        try {
            //一定要写完啊，别写半个
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
        } catch (IOException e) {
            LOGGER.error("下载文件出错-初始化异常!", e);
        }finally {
            try {
                //注意关闭啊
                outputStream.close();
            }catch (Exception e){
                LOGGER.error("下载文件出错-关闭outputStream发生异常!", e);
            }

        }

    }

    //设置单元格值
    private void setCellValue(HSSFCell hssfCell, String fieldName, T object) {
        Object resultObject = null;
        try {
            resultObject = object.getClass().getMethod("get" + StringUtils.capitalize(fieldName)).invoke(object);

        } catch (Exception e) {
            LOGGER.error("未找到 " + "get" + StringUtils.capitalize(fieldName) + " 方法!", e);
        }
        if (resultObject instanceof Integer) {
            hssfCell.setCellValue((Integer) resultObject);
        } else if (resultObject instanceof Double) {
            hssfCell.setCellValue((Double) resultObject);
        } else if (resultObject instanceof Float) {
            hssfCell.setCellValue((Float) resultObject);
        } else if (resultObject instanceof Long) {
            hssfCell.setCellValue((Long) resultObject);
        } else if (resultObject instanceof Boolean) {
            Boolean resultBoolean = (Boolean) resultObject;
            if (resultBoolean) {
                hssfCell.setCellValue("是");
            } else {
                hssfCell.setCellValue("否");
            }
        } else if (resultObject instanceof Date) {
            Date resultDate = (Date) resultObject;
            String resultDateString = resultDate.toString();
            hssfCell.setCellValue(resultDateString);
        } else if (resultObject instanceof Calendar) {
            hssfCell.setCellValue((Calendar) resultObject);
        } else if (resultObject instanceof Collection) {
            StringBuilder collectionString = new StringBuilder();

            Iterator iterator = ((Collection) resultObject).iterator();
            while (iterator.hasNext()) {
                collectionString.append(iterator.next() + ",");
            }
            if (collectionString.length() < 1) {
                hssfCell.setCellValue("");
            } else {
                hssfCell.setCellValue(collectionString.substring(0, collectionString.length() - 1));
            }

        } else if (null == resultObject) {
            hssfCell.setCellValue("");
        } else {
            hssfCell.setCellValue(resultObject.toString());
        }
    }
}