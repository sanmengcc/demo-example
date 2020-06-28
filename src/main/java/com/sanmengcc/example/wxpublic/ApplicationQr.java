package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * description: 二维码相关接口demo <br>
 * date: 2020/6/19 0:24 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class ApplicationQr {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String accessToken = ApplicationMediaUploadAndDown.getAccessToken();
        ApplicationMediaUploadAndDown.accessToken = accessToken;
        System.out.println(accessToken);
        urlCoverShort();
    }

    /**
     * 长链接转成短链接
     * @return
     * @throws IOException
     */
    public static String urlCoverShort() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("action", "long2short");
        param.put("long_url", "https://www.baidu.com");

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
     * 通过ticket换取二维码
     * @return
     * @throws IOException
     */
    public static String ticketExchangeQr() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEX8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQ1RPMmNrSG5lNGkxMDAwME0wN2sAAgRtofheAwQAAAAA";
        OkHttpClient client = new OkHttpClient();
        String content = param.toJSONString();
        System.out.println(content);
        Request request = (new Request.Builder()).url(url).build();
        Response response = client.newCall(request).execute();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File file = new File("D:\\" + System.currentTimeMillis() + ".png");
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (is != null)
                    is.close();
                if (fos != null)
                    fos.close();
            } catch (Exception exception) {

            }
        }
        return "result";
    }

    /**
     * 获取永久二维码(STR)
     * @return
     * @throws IOException
     */
    public static String createScentStrForever() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("action_name", "QR_LIMIT_STR_SCENE");
        JSONObject scene = new JSONObject();
        scene.put("scene_str", "xxxxxxxxxxxxxx");
        JSONObject sceneInfo = new JSONObject();
        sceneInfo.put("scene", scene);
        param.put("action_info", sceneInfo);
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
     * 获取永久二维码(id)
     * @return
     * @throws IOException
     */
    public static String createScentIdForever() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("action_name", "QR_LIMIT_SCENE");
        JSONObject scene = new JSONObject();
        scene.put("scene_id", 123);
        JSONObject sceneInfo = new JSONObject();
        sceneInfo.put("scene", scene);
        param.put("action_info", sceneInfo);
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
     * 获取临时二维码(Str)
     * @return
     * @throws IOException
     */
    public static String createScentStrTemp() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("expire_seconds", 604800);
        param.put("action_name", "QR_STR_SCENE");
        JSONObject scene = new JSONObject();
        scene.put("scene_str", "XXXX");
        JSONObject sceneInfo = new JSONObject();
        sceneInfo.put("scene", scene);
        param.put("action_info", sceneInfo);
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
     * 获取临时二维码(id)
     * @return
     * @throws IOException
     */
    public static String createScentIdTemp() throws IOException {
        JSONObject param = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                + ApplicationMediaUploadAndDown.accessToken;
        param.put("expire_seconds", 604800);
        param.put("action_name", "QR_SCENE");
        JSONObject scene = new JSONObject();
        scene.put("scene_id", 1);
        JSONObject sceneInfo = new JSONObject();
        sceneInfo.put("scene", sceneInfo);
        param.put("action_info", sceneInfo);
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

    //{"ticket":"gQEj8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyYm9mSWRGSG5lNGkxY2dvMU52MU4AAgSQnfheAwSAOgkA","expire_seconds":604800,"url":"http:\/\/weixin.qq.com\/q\/02bofIdFHne4i1cgo1Nv1N"}

}
