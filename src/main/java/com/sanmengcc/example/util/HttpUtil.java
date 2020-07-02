package com.sanmengcc.example.util;

import okhttp3.*;
import java.io.IOException;

/**
 * @ClassNameHttpUtil
 * @Description
 * @Author sanmengcc
 * @Date2020/7/1 9:18
 * @Version V1.0
 **/
public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String post(String url, String postStr) throws IOException {
        OkHttpClient client = new OkHttpClient();
        System.out.println("com.sanmengcc.example.util.HttpUtil : " + postStr);
        RequestBody body = RequestBody.create(JSON, postStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        System.out.println("com.sanmengcc.example.util.HttpUtil : " + result);
        return result;
    }

    public static String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url(url).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        System.out.println("com.sanmengcc.example.util.HttpUtil : " + result);
        return result;
    }
}
