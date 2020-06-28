package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * description: 群发素材接口demo <br>
 * date: 2020/6/19 0:24 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class ApplicationBulkMessage {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String accessToken = ApplicationMediaUploadAndDown.getAccessToken();
        ApplicationMediaUploadAndDown.accessToken = accessToken;
        System.out.println(accessToken);
        preview();
    }

    /**
     * 数据预览
     * @return
     * @throws IOException
     */
    public static String preview() throws IOException {
        JSONObject param = new JSONObject();
        param.put("touser", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ");
        param.put("msgtype", "mpnews");
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id","0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4");
        param.put("mpnews", mpnews);
        param.put("msg_id", 30214);
        param.put("article_idx", 2);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }


    /**
     * 删除群发
     * @return
     * @throws IOException
     */
    public static String delBulk() throws IOException {
        JSONObject param = new JSONObject();
        param.put("msg_id", 30214);
        param.put("article_idx", 2);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 群发图文推文信息(openid)
     * 测试号不允许发送没有权限
     * @return
     * @throws IOException
     */
    public static String bulkNewsOpenid() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id","0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4");
        param.put("touser", Arrays.asList("o6xeE0iTc9vfeOpnUxAgDPG7WPIQ", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ"));
        param.put("mpnews", mpnews);
        param.put("msgtype","mpnews");https:
        param.put("send_ignore_reprint", 0);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 群发图文推文信息(标签)
     * 测试号不允许发送没有权限
     * @return
     * @throws IOException
     */
    public static String bulkNewsTag() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject filter = new JSONObject();
        filter.put("is_to_all",false);
        filter.put("tag_id", 2);
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id","0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4");
        param.put("filter", filter);
        param.put("mpnews", mpnews);
        param.put("msgtype","mpnews");
        param.put("send_ignore_reprint", 0);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 群发图文推文信息
     * 测试号不允许发送没有权限
     * @return
     * @throws IOException
     */
    public static String bulkNews() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject filter = new JSONObject();
        filter.put("is_to_all",true);
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id","0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4");
        param.put("filter", filter);
        param.put("mpnews", mpnews);
        param.put("msgtype","mpnews");
        param.put("send_ignore_reprint", 0);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }
}
