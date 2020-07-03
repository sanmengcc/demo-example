package com.sanmengcc.example.wxapplet;

import com.alibaba.fastjson.JSONObject;
import com.sanmengcc.example.util.FileUtil;
import com.sanmengcc.example.util.HttpUtil;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @ClassNameApplication
 * @Description 微信小程序二维码相关接口
 * @Author sanmengcc
 * @Date2020/6/30 21:27
 * @Version V1.0
 **/
public class ApplicationQr {

    public static String accessToken = "34_URJtcjRkeFWWBX0yu56fB_xTbPtUeDVCsbAI32gV34LXgkVpBHVjPjKu-UeKhg92FNEdQJtgGlfOKhCyCh71yrY68aRZOnFaF1kziZ8yjwPANE-y1Rf8M2vGtYCpxMk5LXYNK3-Z2EEhCPisFQWjABAQBH";

    private final static String appid = "xxxxxxxxxx";

    private final static String secret = "xxxxxxxxx";


    public static void main(String[] args) throws IOException {
        getWxMiniQr3();
    }

    /**
     * @Description 创建小程序二维码
     * @Author  sanmengcc
     * @Date   2020/7/3 9:33
     */
    public static byte[] getWxMiniQr3() {
        try{
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            JSONObject paramJson = new JSONObject();
            paramJson.put("path", "pages/welcome/welcome?page=1");
            paramJson.put("width", "430");
            paramJson.put("scene", "WX00001");
            paramJson.put("auto_color", true);
            paramJson.put("is_hyaline", true);
            paramJson.put("line_color", new JSONObject(){{
                put("r", "0");
                put("g", "0");
                put("b", "0");
            }});
            printWriter.write(paramJson.toString());
            printWriter.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            //不要使用input来读取数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = -1;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                out.write(data, 0, len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            byte[] bytes = out.toByteArray();
            System.out.println(new String(bytes, "UTF-8"));
            FileUtil.byte2File(bytes, "sanmengcc002.png");
            return bytes;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * @Description 创建小程序二维码
     * @Author  sanmengcc
     * @Date   2020/7/3 9:33
     */
    public static byte[] getWxMiniQr2() {
        try{
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            JSONObject paramJson = new JSONObject();
            paramJson.put("path", "pages/welcome/welcome?page=1");
            paramJson.put("width", "430");
            paramJson.put("auto_color", true);
            paramJson.put("is_hyaline", true);
            paramJson.put("line_color", new JSONObject(){{
                put("r", "0");
                put("g", "0");
                put("b", "0");
            }});
            printWriter.write(paramJson.toString());
            printWriter.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            //不要使用input来读取数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = -1;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                out.write(data, 0, len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            byte[] bytes = out.toByteArray();
            System.out.println(new String(bytes, "UTF-8"));
            FileUtil.byte2File(bytes, "sanmengcc002.png");
            return bytes;
        }catch (Exception e){

        }
        return null;
    }


    /**
     * @Description 创建小程序二维码
     * @Author  sanmengcc
     * @Date   2020/7/3 9:33
     */
    public static byte[] getWxMiniQr1() {
        try{
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            JSONObject paramJson = new JSONObject();
            paramJson.put("path", "pages/welcome/welcome?page=1");
            paramJson.put("width", "430");

            printWriter.write(paramJson.toString());
            printWriter.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            //不要使用input来读取数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = -1;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                out.write(data, 0, len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            byte[] bytes = out.toByteArray();
            System.out.println(new String(bytes, "UTF-8"));
            FileUtil.byte2File(bytes, "sanmengcc002.png");
            return bytes;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * @Description 获取小程序accessToken
     * @Author  sanmengcc
     * @Date   2020/7/1 9:37
     */
    public static void getAccessToken() throws IOException {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String result = HttpUtil.get(tokenUrl);
        Map resultMap = JSONObject.parseObject(result, Map.class);
        String access_token = (String) resultMap.get("access_token");
        System.out.println("accessToken = " + access_token);
    }

}
