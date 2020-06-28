package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * description: 统计分析接口demo <br>
 * date: 2020/6/19 0:24 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class ApplicationAnalyze {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String accessToken = ApplicationMediaUploadAndDown.getAccessToken();
        ApplicationMediaUploadAndDown.accessToken = accessToken;
        System.out.println(accessToken);
        interfaceAnalyze();
    }

    public static void interfaceAnalyze() throws IOException {
        //获取接口分析数据 时间跨度 30
        getAnalyze("https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=");
        //获取接口分析分时数据 时间跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=");
    }

    public static void advertisingAnalyze() throws IOException {
        JSONObject param = new JSONObject();
        param.put("page", 1);
        param.put("page_size", 90);
        param.put("start_date", 2020-06-27);
        param.put("end_date", 2020-06-27);
        param.put("ad_slot", "SLOT_ID_WEAPP_BOX");//如果不传递广告位类型名称，将默认返回全部类型广告位（除返佣商品广告）的数据。

        //获取公众号分广告位数据 时间跨度 90
        getAnalyze("https://api.weixin.qq.com/publisher/stat?action=publisher_adpos_general&access_token=", param.toJSONString());
        //获取公众号返佣商品数据 时间跨度 60
        getAnalyze("https://api.weixin.qq.com/publisher/stat?action=publisher_cps_general&access_token=", param.toJSONString());
        //获取公众号结算收入数据及结算主体信息 时间跨度 无
        getAnalyze("https://api.weixin.qq.com/publisher/stat?action=publisher_settlement&access_token=", param.toJSONString());

    }

    /**
     *  数据分析
     * @return
     * @throws IOException
     */
    public static String getAnalyze(String reqUrl,String json) throws IOException {
        String url = reqUrl + ApplicationMediaUploadAndDown.accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }


    /**
     * 消息分析
     */
    public static void messageAnalyze() throws IOException {
        //获取消息发送概况数据 时间最大跨度 7
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsg?access_token=");
        //获取消息分送分时数据 时间最大跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=");
        //获取消息发送周数据 时间最大跨度 30
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=");
        //获取消息发送月数据 时间最大跨度 30
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=");
        //获取消息发送分布数据 时间最大跨度 15
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=");
        //获取消息发送分布周数据 时间最大跨度 30
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=");
        //获取消息发送分布月数据 时间最大跨度 30
        getAnalyze("https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=");
    }

    /**
     *  图文分析
     * @return
     * @throws IOException
     */
    public static void newsAnalyze() throws IOException {
        //获取图文群发每日数据 时间最大跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getarticlesummary?access_token=");
        //获取图文群发总数据 时间最大跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getarticletotal?access_token=");
        //获取图文统计数据 时间最大跨度 3
        getAnalyze("https://api.weixin.qq.com/datacube/getuserread?access_token=");
        //获取图文统计分时数据 时间最大跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getuserreadhour?access_token=");
        //获取图文分享转发数据 时间最大跨度 7
        getAnalyze("https://api.weixin.qq.com/datacube/getusershare?access_token=");
        //获取图文分享转发分时数据 时间最大跨度 1
        getAnalyze("https://api.weixin.qq.com/datacube/getusersharehour?access_token=");
    }

    /**
     *  数据分析
     * @return
     * @throws IOException
     */
    public static String getAnalyze(String reqUrl) throws IOException {
        JSONObject param = new JSONObject();
        String url = reqUrl + ApplicationMediaUploadAndDown.accessToken;
        param.put("begin_date", "2020-06-27");
        param.put("end_date", "2020-06-27");

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
     * 获取累计用户数据
     * @return
     * @throws IOException
     */
    public static String getusercumulate() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/datacube/getusercumulate?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("begin_date", "2020-06-22");
        param.put("end_date", "2020-06-27");

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
     * 获取用户增减数据
     * @return
     * @throws IOException
     */
    public static String getusersummary() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/datacube/getusersummary?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("begin_date", "2020-06-22");
        param.put("end_date", "2020-06-27");

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
