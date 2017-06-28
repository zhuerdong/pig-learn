package com.pig.learn.mybatis.Excel;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadExcelUtil<T> {
    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger(ReadExcelUtil.class);

    /**
     * 读取excel返回映射的实体类list
     *
     * @param fieldNameList 实体类的字段list，顺序和excel中列的顺序相同
     * @param file          文件
     * @param clazz         实体类
     * @return
     */
    public List<T> readAll(List<String> fieldNameList, MultipartFile file, Class<T> clazz) {
        for (String fieldName : fieldNameList) {
            if (null == fieldName) {
                throw new IllegalArgumentException("属性名不能为空");
            }
        }
        if (file == null) {
            throw new IllegalArgumentException("请上传文件");
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        List<T> objects = new ArrayList<T>();

        try {
            Workbook wb = null;
            if (".xls".equals(suffix)) {// 2003
                // 读取Excel
                wb = new HSSFWorkbook(file.getInputStream());
            } else if (".xlsx".equals(suffix)) {// 2007
                wb = new XSSFWorkbook(file.getInputStream());
            }
            if (wb == null) {
                return null;
            }
            // 获取sheet数目
            for (int t = 0; t < wb.getNumberOfSheets(); t++) {
                Sheet sheet = wb.getSheetAt(0);//默认用第一个sheet
                int lastRowNum = sheet.getLastRowNum();
                // 循环读取,（去掉第一行）
                Map<String, Field> fieldMap = Maps.newHashMap();//保存实体类的字段信息
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    T entity = null;
                    if (row != null) {
                        // 获取每一列的值
                        for (int j = 0; j < row.getLastCellNum() && j < fieldNameList.size(); j++) {
                            Cell cell = row.getCell(j);
                            if (cell == null) {
                                continue;
                            }
                            String c = getCellValue(cell);
                            if (c == null) {
                                continue;
                            }
                            // 如果不存在实例则新建
                            entity = (entity == null ? clazz.newInstance() : entity);
                            String fieldName = fieldNameList.get(j);
                            Field field = fieldMap.get(fieldName);
                            if (field == null) {
                                field = entity.getClass().getDeclaredField(fieldName);
                                fieldMap.put(fieldName, field);
                            }
                            // 取得类型,并根据对象类型设置值.
                            Class<?> fieldType = field.getType();
                            try {
                                if (String.class == fieldType) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, c);
                                } else if ((Integer.TYPE == fieldType)
                                        || (Integer.class == fieldType)) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, Integer.parseInt(c.substring(0, c.indexOf("."))));
                                } else if ((Long.TYPE == fieldType)
                                        || (Long.class == fieldType)) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, Long.parseLong(c.substring(0, c.indexOf("."))));
                                } else if ((Float.TYPE == fieldType)
                                        || (Float.class == fieldType)) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, Float.valueOf(c));
                                } else if ((Short.TYPE == fieldType)
                                        || (Short.class == fieldType)) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, Short.valueOf(c));
                                } else if ((Double.TYPE == fieldType)
                                        || (Double.class == fieldType)) {
                                    entity.getClass().getMethod("set" + StringUtils.capitalize(fieldName), field.getType()).invoke(entity, Double.valueOf(c));
                                }
                            } catch (Exception e) {
                                //error
                            }
                        }
                        if (entity != null) {
                            objects.add(entity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            ERROR_LOGGER.info("文件读取失败！");
        }
        return objects;
    }

    /***
     * 读取单元格的值
     *
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            //这个地方貌似有问题，注意一下
            case Cell.CELL_TYPE_NUMERIC:
                DecimalFormat df = new DecimalFormat("0");
                result = df.format(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                result = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_ERROR:
                result = cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            default:
                break;
            }
        }
        return result.toString();
    }
}
