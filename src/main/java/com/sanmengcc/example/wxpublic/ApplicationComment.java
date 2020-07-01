package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassNameApplicationPush
 * @Description
 * @Author sanmengcc
 * @Date2020/6/30 21:54
 * @Version V1.0
 **/
public class ApplicationComment {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String accessToken = ApplicationMediaUploadAndDown.getAccessToken();


    public static void main(String[] args) throws IOException {
        commentDelReply();
    }


    /**
     * @Description 删除回复
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentDelReply() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("user_comment_id", 0);
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/reply/delete?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 回复评论
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentReply() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("user_comment_id", 0);
        param.put("content", "回复");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/reply/add?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 删除评论
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentDelUser() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("user_comment_id", 0);
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/delete?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }



    /**
     * @Description 将评论取消标记精选
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentUnStar() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("user_comment_id", 0);
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/unmarkelect?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 将评论标记精选
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentStar() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("user_comment_id", 0);
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/markelect?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 查看指定文章的评论数据
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentAnalyze() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", 1);
        param.put("index", 1);
        param.put("begin", 0);
        param.put("count", 30);
        param.put("type", 1);//type=0 普通评论&精选评论 type=1 普通评论 type=2 精选评论
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/list?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 关闭已群发文章评论
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentClose() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", "1");
        param.put("index", "4");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/close?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 打开已群发文章评论
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void commentOpen() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_data_id", "1");
        param.put("index", "4");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/comment/open?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

}
