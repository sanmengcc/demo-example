package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * description: 用户相关接口demo <br>
 * date: 2020/6/19 0:24 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class ApplicationUser {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String accessToken = ApplicationMediaUploadAndDown.getAccessToken();
        ApplicationMediaUploadAndDown.accessToken = accessToken;
        System.out.println(accessToken);
        unSetBlacklist();
    }

    /**
     * 取消用户黑名单
     * @return
     * @throws IOException
     */
    public static String unSetBlacklist() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("openid_list", Arrays.asList("o6xeE0iTc9vfeOpnUxAgDPG7WPIQ"));

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
     * 设置黑名单用户
     * @return
     * @throws IOException
     */
    public static String setBlacklist() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("openid_list", Arrays.asList("o6xeE0iTc9vfeOpnUxAgDPG7WPIQ"));

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
     * 获取黑名单列表
     * @return
     * @throws IOException
     */
    public static String getBlacklistNext() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("next_openid", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ");
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
     * 获取黑名单列表
     * @return
     * @throws IOException
     */
    public static String getBlacklist() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("begin_openid", "");
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
     * 批量获取用户的详细信息
     * @return
     * @throws IOException
     */
    public static String batchGetUserInfo() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        JSONObject user = new JSONObject();
        user.put("openid", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ");
        user.put("lang", "zh_CN");
        param.put("user_list", user);
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
     * 获取用户的详细信息
     * @return
     * @throws IOException
     */
    public static String getUserInfo() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
                + ApplicationMediaUploadAndDown.accessToken
                + "&openid=o6xeE0iTc9vfeOpnUxAgDPG7WPIQ&lang=zh_CN";
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
     * 获取用户的列表
     * @return
     * @throws IOException
     */
    public static String getUserList() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
                + ApplicationMediaUploadAndDown.accessToken
                + "&next_openid=";
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
     * 获取用户的标签
     * @return
     * @throws IOException
     */
    public static String setUserRemark() throws IOException {
        JSONObject param = new JSONObject();
        param.put("openid", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ");
        param.put("remark", "sanmengcc");
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 获取用户的标签
     * @return
     * @throws IOException
     */
    public static String getUserTag() throws IOException {
        JSONObject param = new JSONObject();
        param.put("openid", "o6xeE0iTc9vfeOpnUxAgDPG7WPIQ");
        String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 用户绑定标签，标签功能目前支持公众号为用户打上最多20个标签。
     * @return
     * @throws IOException
     */
    public static String bindTag() throws IOException {
        JSONObject param = new JSONObject();
        param.put("openid_list", Arrays.asList("o6xeE0iTc9vfeOpnUxAgDPG7WPIQ","o6xeE0iTc9vfeOpnUxAgDPG7WPIQ"));
        param.put("tagid", 102);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 用户解绑标签
     * @return
     * @throws IOException
     */
    public static String unBindTag() throws IOException {
        JSONObject param = new JSONObject();
        param.put("openid_list", Arrays.asList("o6xeE0iTc9vfeOpnUxAgDPG7WPIQ","o6xeE0iTc9vfeOpnUxAgDPG7WPIQ"));
        param.put("tagid", 102);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 获取标签下的用户粉丝
     * @return
     * @throws IOException
     */
    public static String getTagUser() throws IOException {
        JSONObject param = new JSONObject();
        param.put("tagid", 102);
        param.put("next_openid", "");
        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 删除标签
     * @return
     * @throws IOException
     */
    public static String delTag() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject tag = new JSONObject();
        tag.put("id", 101);
        param.put("tag", tag);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + ApplicationMediaUploadAndDown.accessToken;
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
     * 编辑标签
     * @return
     * @throws IOException
     */
    public static String updateTag() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject tag = new JSONObject();
        tag.put("name", "USERxxx");
        tag.put("id", 101);
        param.put("tag", tag);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=" + ApplicationMediaUploadAndDown.accessToken+"&name=USER";
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
     * 获取所有标签
     * @return
     * @throws IOException
     */
    public static String getAllTag() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, new JSONObject().toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 创建用户标签
     * @return
     * @throws IOException
     */
    public static String createTag() throws IOException {
        JSONObject param = new JSONObject();
        JSONObject tag = new JSONObject();
        tag.put("name", "USER");
        param.put("tag", tag);
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + ApplicationMediaUploadAndDown.accessToken+"&name=USER";
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
     34_9JBfBtuXoIsmI15vmSNdC27wc0-mwCFliAIbd4zHyST0FOuVhsEWP1jF2Oyq0bp_Gi_BgVsRZyqDlHAeZt6tj0Gk-8APzbfhQJOJ0MgDXOL1nnjpcUtyt_6DyT2JIq3X92CsGfeZG7S1gEO4YOScABARHL
     {"tag":{"name":"USER"}}
     {"tag":{"id":101,"name":"USER"}}
     */
}
