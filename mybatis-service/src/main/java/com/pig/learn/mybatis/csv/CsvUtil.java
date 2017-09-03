package com.pig.learn.mybatis.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;


import java.util.List;

/**
 * @Author: pig
 * @Date: Created in 下午4:03 16/12/16
 * @Description:
 */
public final class CsvUtil {

    private static final String CSV_CHARSET = "utf-8";
    public static final String CSV_SPLIT_COMMA = ",";

    private CsvUtil() {
    }

    /**
     * 读取csv文件内容，
     *
     * @param file       文件
     * @param csvSplit   分隔符
     * @param isReadHead 是否包含文件头
     * @return list
     */
    public static List<Object> readCsvList(
            File file, String csvSplit, boolean isReadHead, boolean charsetFormatCorrect, List<String> errorList) {
        String charset = getCharset(file);
        boolean skipName = false;
        if (StringUtils.isBlank(charset) || "OTHER".equals(charset)) {
            charset = CSV_CHARSET;
            skipName = true;
            charsetFormatCorrect = false;
        }
        List<String[]> records = readCsv(file, csvSplit, isReadHead, charset);
        List<Object> list = Lists.newArrayListWithCapacity(records.size());
        for (String[] row : records) {

        }
        return list;
    }

    /**
     * 获取文件编码格式
     *
     * @param file 待解析文件
     * @return 编码格式
     */
    private static String getCharset(File file) {

        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] b = new byte[200];
            in.read(b);
            EncodingDetect bytesEncodingDetect = new EncodingDetect();
            int i = bytesEncodingDetect.detectEncoding(b);
            return bytesEncodingDetect.getCoding(i);
        } catch (IOException e) {
            //ERROR_LOG.error("CsvUtil.getCharset error", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //ERROR_LOG.error("CsvUtil.getCharset close error", e);
                }
            }
        }
        return "";
    }

    /**
     * 读取CSV文件
     *
     * @param file       CSV文件
     * @param csvSplit   CSV文件分隔符
     * @param isReadHead 是否读取CSV文件头部
     * @return
     */
    public static List<String[]> readCsv(File file, String csvSplit, boolean isReadHead) {
        return readCsv(file, csvSplit, isReadHead, CSV_CHARSET);
    }

    /**
     * 读取CSV文件
     *
     * @param file       CSV文件
     * @param csvSplit   CSV文件分隔符
     * @param isReadHead 是否读取CSV文件头部
     * @param charset    编码格式
     * @return
     */
    private static List<String[]> readCsv(File file, String csvSplit, boolean isReadHead, String charset) {
        List<String[]> csvList = new ArrayList<String[]>();
        if (null != file) {
            // 读取文件
            CsvReader reader = null;
            try {
                InputStream inputStream = new FileInputStream(file);
                reader = new CsvReader(inputStream, csvSplit.toCharArray()[0], Charset.forName(charset));
                // 跳过表头
                if (!isReadHead) {
                    reader.readHeaders();
                }
                // 逐行读入除表头的数据
                while (reader.readRecord()) {
                    csvList.add(reader.getValues());
                }
            } catch (Exception e) {

            } finally {
                // 关闭流
                if (reader != null) {
                    reader.close();
                }
            }
        }
        return csvList;
    }


    public static boolean writeCsv(OutputStream os, Write write) {
        CsvWriter wr = null;
        try {
            wr =new CsvWriter(os, CSV_SPLIT_COMMA.toCharArray()[0], Charset.forName(CSV_CHARSET));
            write.writeCsv(wr);
            return true;
        } catch (Exception e) {

        } finally {
            if (wr != null) {
                wr.close();
            }
        }
        return false;
    }

    public interface Write {
        boolean writeCsv(CsvWriter wr);
    }
}