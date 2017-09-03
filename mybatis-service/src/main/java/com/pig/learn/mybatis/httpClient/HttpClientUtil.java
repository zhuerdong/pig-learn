package com.pig.learn.mybatis.httpClient;

import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager connectionPoolManager =
            new PoolingHttpClientConnectionManager();
    //设置最大路由数，如果不明白，不建议配置
    //connectionPoolManager.setDefaultMaxPerRoute(100);
    //connectionPoolManager.setMaxTotal(100);
    //设置httpclient

    private static RequestConfig commonRequestConfig = RequestConfig.custom()
            //设置整个socket的时间
            .setSocketTimeout(500)
            //设置建立练级的超时时间
            .setConnectTimeout(500)
            //设置链接服务的超时时间
            .setConnectionRequestTimeout(500)
            .build();


    /**
     * 注意client使用的时线程池，所以不client不要关闭
     * @param url
     * @return
     */
    public static String sendGetRequest(String url){
        String result = null;
        CloseableHttpResponse response = null;

        try {

            CloseableHttpClient httpClient = HttpClients.custom()
                    //设置链接管理池
                    .setConnectionManager(connectionPoolManager)
                    .setDefaultRequestConfig(commonRequestConfig)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0,false))
                    .build();

            HttpGet getMethod =new HttpGet(url);
            response = httpClient.execute(getMethod);
            int statusCode = response.getStatusLine().getStatusCode();
            if(200 == statusCode){
                HttpEntity entity = response.getEntity();
                if(null != entity){
                    result = EntityUtils.toString(entity,"UTF-8");
                }
            }

        }catch (Exception e){

        }

        return  result;
    }

    /**
     * 注意client使用的时线程池，所以不client不要关闭
     * @param url
     * @return
     */
    public static String sendPostRequest(String url, List<NameValuePair> formparms){
        String result = null;
        CloseableHttpResponse response = null;
        try {

            CloseableHttpClient httpClient = HttpClients.custom()
                    //设置链接管理池
                    .setConnectionManager(connectionPoolManager)
                    .setDefaultRequestConfig(commonRequestConfig)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0,false))
                    .build();

            HttpPost httpPost =new HttpPost(url);

            //创建参数队列
            //List<NameValuePair> formparms = Lists.newArrayList();
            //formparms.add(new BasicNameValuePair("",""));

            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparms,"UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            response = httpClient.execute(httpPost);
            
            int statusCode = response.getStatusLine().getStatusCode();
            if(200 == statusCode){
                HttpEntity entity = response.getEntity();
                if(null != entity){
                    result = EntityUtils.toString(entity,"UTF-8");
                }
            }

        }catch (Exception e){

        }

        return  result;
    }
}
