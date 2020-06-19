package com.sanmengcc.example.util;

import okhttp3.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description: OkHttpClientUtil <br>
 * date: 2020/6/17 10:29 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class OkHttpClientUtil {

    private static int connTimeOut = 5;
    private static int readTimeOut = 20;
    private static int writeTimeOut = 10;
    public static OkHttpClient client = null;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(5L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public OkHttpClientUtil() {
    }


    public static String doGet(String host, String path, Map<String, String> headers, Map<String, String> querys) throws Exception {
        StringBuffer url = new StringBuffer(host + (path == null?"":path));
        if(querys != null) {
            url.append("?");
            Iterator iterator = querys.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> e = (Map.Entry)iterator.next();
                url.append((String)e.getKey()).append("=").append((String)e.getValue() + "&");
            }
            url = new StringBuffer(url.substring(0,url.length()-1));
        }
        Request.Builder requestBuilder = new Request.Builder();
        if(headers != null && headers.size() > 0) {
            Iterator iterator = headers.keySet().iterator();
            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                requestBuilder.addHeader(key, (String)headers.get(key));
            }
        }
        Request request = (requestBuilder).url(url.toString()).build();
        Response response = client.newCall(request).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    public static String doGet(String urlstr, Map<String, String> headers) throws Exception {
        StringBuffer url = new StringBuffer(urlstr);
        Request.Builder requestBuilder = new Request.Builder();
        if(headers != null && headers.size() > 0) {
            Iterator iterator = headers.keySet().iterator();
            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                requestBuilder.addHeader(key, (String)headers.get(key));
            }
        }
        Request request = (requestBuilder).url(url.toString()).build();
        Response response = client.newCall(request).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    public static String doPost(String url, Map<String, String> headers, Map<String, String> querys) throws Exception {
        FormBody.Builder formbody = new FormBody.Builder();
        if(null != querys){
            Iterator iterator = querys.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry)iterator.next();
                formbody.add((String)elem.getKey(), (String)elem.getValue());
            }
        }

        RequestBody body = formbody.build();
        Request.Builder requestBuilder = (new Request.Builder()).url(url);
        if(headers != null && headers.size() > 0) {
            Iterator iteratorHeader = headers.keySet().iterator();
            while(iteratorHeader.hasNext()) {
                String key = (String)iteratorHeader.next();
                requestBuilder.addHeader(key, (String)headers.get(key));
            }
        }

        Request requet = requestBuilder.post(body).build();
        Response response = client.newCall(requet).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    /**
     * @param url  请求URL
     * @param querys 请求参数
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> querys) throws Exception {
        FormBody.Builder formbody = new FormBody.Builder();
        if(null != querys){
            Iterator iterator = querys.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry)iterator.next();
                formbody.add((String)elem.getKey(), (String)elem.getValue());
            }
        }
        RequestBody body = formbody.build();
        Request.Builder requestBuilder = (new Request.Builder()).url(url);
        Request requet = requestBuilder.post(body).build();
        Response response = client.newCall(requet).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    public static String doPost(String url, Map<String, String> headers, String sendMessage) throws Exception {

        RequestBody body = FormBody.create(MediaType.parse("application/json"), sendMessage);;
        Request.Builder requestBuilder = (new Request.Builder()).url(url);
        if(headers != null && headers.size() > 0) {
            Iterator iteratorHeader = headers.keySet().iterator();
            while(iteratorHeader.hasNext()) {
                String key = (String)iteratorHeader.next();
                requestBuilder.addHeader(key, (String)headers.get(key));
            }
        }

        Request requet = requestBuilder.post(body).build();
        Response response = client.newCall(requet).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    public static String doPut(String host, String path, Map<String, String> headers, Map<String, String> querys) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        Iterator iterator = querys.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry)iterator.next();
            builder.add((String)elem.getKey(), (String)elem.getValue());
        }

        RequestBody body = builder.build();
        Request.Builder requestBuilder = (new Request.Builder()).url(host + path);
        if(headers != null && headers.size() > 0) {
            Iterator iteratorHeader = headers.keySet().iterator();
            while(iteratorHeader.hasNext()) {
                String key = (String)iteratorHeader.next();
                requestBuilder.addHeader(key, (String)headers.get(key));
            }
        }

        Request requet = requestBuilder.put(body).build();
        Response response = client.newCall(requet).execute();
        String responseStr = response.body() == null?"":response.body().string();
        return responseStr;
    }

    public static String sendHttpPostJson(String url, String json) {
        RequestConfig.Builder custom = RequestConfig.custom();

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(custom.build());
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
