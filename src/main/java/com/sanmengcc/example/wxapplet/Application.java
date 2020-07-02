package com.sanmengcc.example.wxapplet;

import com.alibaba.fastjson.JSONObject;
import com.sanmengcc.example.util.HttpUtil;
import com.sanmengcc.example.util.WxMiniUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassNameApplication
 * @Description 微信小程序Token相关接口
 * @Author sanmengcc
 * @Date2020/6/30 21:27
 * @Version V1.0
 **/
public class Application {

    public static String accessToken = "34_89kXjlrmD-tT20aIQCUidx1JsklSpD48VmLmCYFvvdjrhkAzA2HHACHSQPB7gzb8ukpnGrdvqUE-3Xfe0L_6ENw8r-2djvZ9fTBG64NWLikAXqB9nD9REu4auLfvn_NAZlucOi1zDIasQwFuCGQgACARVF";
    private final static String appid = "xxxxxxxxxxxxx";

    private final static String secret = "xxxxxxxxxxxx";


    public static void main(String[] args) throws IOException {
        getAccessToken();
    }


    /**
     * @Description 访问页面。目前只提供按 page_visit_pv 排序的 top200。 时间跨度一天
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidvisitpage() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200630");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }


    /**
     * @Description 获取用户小程序访问分布数据 时间跨度限定最大一天
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidvisitdistribution() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200630");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户小程序访问分布数据 时间跨度限定最大一月 从昨日开始
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappiduserportrait() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200601");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序数据月趋势 时间跨度限定一月
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidmonthlyvisittrend() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200601");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序数据周趋势 时间跨度限定一周
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidweeklyvisittrendaccess_token() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200622");
        param.put("end_date", "20200628");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序数据日趋势 时间跨度限定一天
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappiddailyvisittrend() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200630");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 访问小程序数据概况 时间跨度限定一天
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappiddailysummarytrend() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200630");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序周留存 时间跨度限定一周
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidweeklyretaininfo() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200622");
        param.put("end_date", "20200628");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序月留存 时间跨度限定一月
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappidmonthlyretaininfo() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200601");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 获取用户访问小程序日留存 时间跨度限定一天
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getweanalysisappiddailyretaininfo() throws IOException {
        JSONObject param = new JSONObject();
        param.put("begin_date", "20200630");
        param.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo?access_token=" + accessToken;
        String result = HttpUtil.post(url, param.toJSONString());
        System.out.println(result);
    }

    /**
     * @Description 支付操作完成后进行获取unionid
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void payAfterGetUnionid(String openid) throws IOException {
        String url = "https://api.weixin.qq.com/wxa/getpaidunionid?access_token=" + accessToken + "&openid=" + openid;
        String result = HttpUtil.get(url);
        System.out.println(result);
    }


    /**
     * @Description 解密手机号码
     * @Author sanmengcc
     * @Date 2020/7/1 10:14
     */
    public static void getUserPhone(String encryptedData, String session_key, String iv) {
        String result = WxMiniUtil.wxDecrypt(encryptedData, session_key, iv);
        JSONObject json = JSONObject.parseObject(result);
        System.out.println("获取微信用户授权数据 phone：" + json.get("phoneNumber"));
    }

    /**
     * @Description 获取openid unionid
     * @Author sanmengcc
     * @Date 2020/7/1 9:59
     */
    public static void getUserInfo(String appid, String secret, String jscode) throws IOException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + jscode + "&grant_type=authorization_code";
        String result = HttpUtil.get(url);
        System.out.println(result);
    }

    /**
     * @Description 获取小程序accessToken
     * @Author sanmengcc
     * @Date 2020/7/1 9:37
     */
    public static void getAccessToken() throws IOException {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String result = HttpUtil.get(tokenUrl);
        Map resultMap = JSONObject.parseObject(result, Map.class);
        String access_token = (String) resultMap.get("access_token");
        System.out.println("accessToken = " + access_token);
    }

}
