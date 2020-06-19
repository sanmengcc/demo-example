package com.sanmengcc.example.wxpublic;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.sanmengcc.example.util.FileUtil;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 素材相关上传下载删除修改demo <br>
 * date: 2020/6/17 10:16 <br>
 *
 * @author: sanmengcc <br>
 * desc:
 */
public class ApplicationMediaUploadAndDown {

    private final static String BOUNDARY_STR = "------------7da2e536604c8";

    public static String accessToken = "34_qeu40Dz-pKARDunx_4vqjmX13HbRzkrL0TR2ftYOw-x8ShotI7QhCUMWnn_BY-L5w3HVf5DsCjK78glYEz-H10OVOVHVnSkbD58Fh4NhNtU6pz6PiSGeCLEvzhMIlrY0Sei10R6HL7ASVJ82BKTjAAAFOX";

    //这里使用的是测试账号
    private final static String appid = "wx5a1772c5bbd052d6";

    private final static String secret = "fb55b339ed6490d2202ad81092efdb1f";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String accessToken = getAccessToken();
        ApplicationMediaUploadAndDown.accessToken = accessToken;
        System.out.println(accessToken);
    }

    public static void testGetMediaTemp() throws IOException {
        //image
        getMediaTempFile("LrMcsaGN9grzzFPzEktW1F6ZpWAQeFf3dl5HRHKHyxzf6rnt76LKCkb85v9t6rRk","png");
        //voice
        getMediaTempFile("mgjVrOvaIjc8-A8PkLZZIYlTA7nMOUmVOMVDhtFT5XpkThl0kxMhotHw3MqUi3MQ", "mp3");
        //thumb
        getMediaTempFile("3Gh2zJQdWV0jvO9r4JlzDY5veOBhraOJJDsxMczHXHQqKlr7ZqlLQ9jtOoK7hLz4","png");
        //video
        getVideoMediaTemp("YNtDLtQPWw-pZWcyNYDG1m-HzEsSQJpj42UGtRHXus0epkv9CgK5gAqwzoVORY2N");
    }

    /**
     * 获取图片,thumb,音频文件
     * @param mediaId
     * @param suffix
     * @return
     * @throws IOException
     */
    public static String getMediaTempFile(String mediaId,String suffix) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url(url).get().build();
        Response response = client.newCall(request).execute();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File file = new File("D:\\" + System.currentTimeMillis() + "." + suffix);
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
     * 获取视频URL
     * @param media_id
     * @return
     */
    public static String getVideoMediaTemp(String media_id) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken;
        Map param = new HashMap();
        param.put("media_id", media_id);
        String result = HttpUtil.get(url, param);
        System.out.println(result);
        return result;
    }

    /**
     * 临时文件
     * 测试图片，音频，thumb上传
     * @throws IOException
     */
    public static void testUploadFileTemp() throws IOException {
        System.out.println(uploadFileTemp(new File("D:\\123456.png"), "image"));
        System.out.println(uploadFileTemp(new File("D:\\646370.mp3"), "voice"));
        System.out.println(uploadFileTemp(new File("D:\\123456.png"), "thumb"));
        System.out.println(uploadFileTemp(new File("D:\\123.MP4"), "video"));
    }

    /**
     * 上传临时文件素材
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadFileTemp(File file, String type) throws IOException {
        return uploadFile("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken, type, FileUtil.getMultipartEntity(file));
    }


    /**
     * 删除永久素材
     *
     * @param mediaId
     * @return
     * @throws IOException
     */
    public static String delMedia(String mediaId) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + accessToken;
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, param.toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 测试发送图文信息（推文）
     * 多个发送的是宫格图文信息
     */
    public static void updateMediaNews() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("media_id", "0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4");
        // 对于宫格消息这个很重要 : index	是	要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
        jsonObject.put("index", 0);
        WxPublicSendMediaVo vo = new WxPublicSendMediaVo();
        vo.setAuthor("setAuthor——update");
        vo.setContent("setContent->>>>>>>>>>>>>>update");
        vo.setDigest("setDigest-------------------------------->update");
        vo.setNeed_open_comment(1);
        vo.setOnly_fans_can_comment(1);
        vo.setTitle("setTitle----------------------------------->update");
        vo.setShow_cover_pic(1);
        vo.setThumb_media_id("0yXThHza9feOGzsZjoEvch2bpoSmI02LmN0NCNRI92M");
        jsonObject.put("articles", vo);
        System.out.println(JSONObject.toJSONString(jsonObject));
        updateMedia(jsonObject);
    }

    /**
     * 更改图文信息（推文）
     *
     * @param jsonObject
     * @return
     */
    public static String updateMedia(JSONObject jsonObject) {
        String postResult = HttpUtil.post("https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=" + accessToken, jsonObject.toJSONString());
        System.out.println("postResult = "+postResult);
        return postResult;
    }

    /**
     * 根据类型获取素材类型
     * 图文：返回文章信息
     * 图片：返回URL
     * 视频：返回media_id
     * 音频：返回media_id
     *
     * @param type
     * @return
     * @throws IOException
     */
    public static String getMediaListByType(String type) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + accessToken;
        JSONObject param = new JSONObject();
        param.put("type", type);
        param.put("offset", 0);
        param.put("count", 10);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, param.toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

    /**
     * 获取公众号的素材分类总数
     * demo:{"voice_count":8,"video_count":0,"image_count":9,"news_count":2}
     *
     * @return
     */
    public static String getMediaAllCount() {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + accessToken;
        String result = HttpUtil.get(url);
        System.out.println(result);
        return result;
    }

    /**
     * getMediaNews 和获取图片的代码是一样的
     * 不过图文返回了具体的文本信息视频是URL
     *
     * @return
     * @throws IOException
     */
    public static String getMediaNews() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + accessToken;
        String mediaId = "0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4";
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, param.toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(new String(result.getBytes(), "UTF-8"));
        return result;
    }

    /**
     * 测试发送图文信息（推文）
     * List<Map> 多个发送的是宫格图文信息
     */
    public static void sendMediaTest() {
        List<Map> list = new ArrayList<>();
        WxPublicSendMediaVo vo = new WxPublicSendMediaVo();
        vo.setAuthor("sanmengcc");
        vo.setContent("pppppppppppppppppppppppppppppp->>>>>>>>>>>>>>");
        vo.setDigest("digest-------------------------------->");
        vo.setNeed_open_comment(1);
        vo.setOnly_fans_can_comment(1);
        vo.setTitle("title----------------------------------->");
        vo.setShow_cover_pic(1);
        vo.setThumb_media_id("0yXThHza9feOGzsZjoEvch2bpoSmI02LmN0NCNRI92M");
        list.add(JSONObject.parseObject(JSONObject.toJSONString(vo), Map.class));
        sendMedia(list);
    }

    /**
     * 发送图文信息（推文）
     *
     * @param list
     * @return
     */
    public static String sendMedia(List<Map> list) {
        String sendRequestParam = "{\"articles\":" + JSONObject.toJSONString(list) + "}";
        String postResult = HttpUtil.post("https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + accessToken, sendRequestParam);
        System.out.println("postResult = " + postResult);
        return postResult;
    }

    /**
     * 获取资源文件（永久素材 仅限音频类）
     *
     * @param mediaId
     * @return
     * @throws IOException
     */
    public static String getMediaVoice(String mediaId) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + accessToken;
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, param.toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File file = new File("D:\\voice.mp3");
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
     * 获取资源URL或者数据
     * 返回URL
     *
     * @return
     * @throws IOException
     */
    public static String getMediaVideo() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + accessToken;
        String mediaId = "0yXThHza9feOGzsZjoEvcsJXWbJci_8KuaBugxgxEg4";
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, param.toJSONString());
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(new String(result.getBytes(), "UTF-8"));
        return result;
    }

    /**
     * 测试上传图文素材中的图片（不限制数量）
     *
     * @throws IOException
     */
    public static void uploadFileNewsPic() throws IOException {
        File file = new File("D:\\123456.png");
        String response = uploadFileNewsPic(file, "image");
        System.out.println(response);
    }

    /**
     * 上传图文素材中的图片（不限制数量）
     *
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadFileNewsPic(File file, String type) throws IOException {
        return uploadFile("http://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken, type, FileUtil.getMultipartEntity(file));
    }

    /**
     * Thumb 图片上传
     *
     * @throws IOException
     */
    public static void testUploadVideo() throws IOException {
        File file = new File("D:\\123.mp4");
        String response = uploadFileVideo(file, "video");
        System.out.println(response);
    }

    /**
     * 上传视频
     *
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadFileVideo(File file, String type) throws IOException {
        return uploadFileVideo("http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken, type, FileUtil.getMultipartEntity(file));
    }


    /**
     * Thumb 图片上传
     *
     * @throws IOException
     */
    public static void testUploadThumb() throws IOException {
        File file = new File("D:\\1.png");
        String response = uploadFile(file, "thumb");
        System.out.println(response);
    }


    /**
     * 上传voice
     *
     * @throws IOException
     */
    public static void testUploadVoice() throws IOException {
        File file = new File("D:\\123456.mp3");
        String response = uploadFile(file, "voice");
        System.out.println(response);
    }

    /**
     * 使用URL进行文件上传转化为字节数组
     *
     * @throws IOException
     */
    public static void testUploadFileUrl() throws IOException {
        byte[] data = FileUtil.getImageStreamByte("https://upload-images.jianshu.io/upload_images/10118469-2f291937eb7ed362.png?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240");
        String response = uploadFile(data, "image", "123456.png");
        System.out.println(response);
    }

    /**
     * 使用流进行上传，最终还是采用字节数组方式
     * MultipartEntityBuilder使用流进行构建导致没有响应数据，暂时没有去研究为什么
     *
     * @throws IOException
     */
    public static void testUploadFileStream() throws IOException {
        InputStream fis = new FileInputStream(new File("D:\\123456.png"));
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();
        String response = uploadFile(data, "image", "123456.png");
        System.out.println(response);
    }

    /**
     * 字节数组文件Test
     *
     * @throws IOException
     */
    public static void testUploadFileByte() throws IOException {
        File file = new File("D:\\123456.png");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String response = uploadFile(data, "image", "123456.png");
        System.out.println(response);
    }

    /**
     * File文件Test
     *
     * @throws IOException
     */
    public static void testUploadFile() throws IOException {
        File file = new File("D:\\1.png");
        String response = uploadFile(file, "image");
        System.out.println(response);
    }

    /**
     * 流形式上传
     *
     * @param stream
     * @param type
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String uploadFileStream(InputStream stream, String type, String fileName) throws IOException {
        return uploadFile("http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken, type, FileUtil.getMultipartEntity(stream, fileName));
    }


    /**
     * 字节数组上传文件
     *
     * @param data
     * @param type
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String uploadFile(byte[] data, String type, String fileName) throws IOException {
        return uploadFile("http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken, type, FileUtil.getMultipartEntity(data, fileName));
    }

    /**
     * File文件上传
     *
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadFile(File file, String type) throws IOException {
        return uploadFile("http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken, type, FileUtil.getMultipartEntity(file));
    }

    /**
     * 文件执行上传操作
     *
     * @param requestUrl
     * @param type
     * @param meb
     * @return
     * @throws IOException
     */
    protected static String uploadFile(String requestUrl, String type, MultipartEntityBuilder meb) throws IOException {
        HttpPost post = new HttpPost(requestUrl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "multipart/form-data;boundary=" + BOUNDARY_STR);
        post.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        meb.addTextBody("type", type);
        post.setEntity(meb.build());
        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        EntityUtils.consume(entity);
        return result;
    }

    /**
     * 文件执行上传操作
     *
     * @param requestUrl
     * @param type
     * @param meb
     * @return
     * @throws IOException
     */
    protected static String uploadFileVideo(String requestUrl, String type, MultipartEntityBuilder meb) throws IOException {
        HttpPost post = new HttpPost(requestUrl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "multipart/form-data;boundary=" + BOUNDARY_STR);
        post.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        meb.addTextBody("type", type);
        meb.addTextBody("description", "{\"title\":\"标题\",\"introduction\":\\\"是的\"}");
        post.setEntity(meb.build());
        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        EntityUtils.consume(entity);
        return result;
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getAccessToken() {
        StringBuffer sb = new StringBuffer("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        sb.append(appid).append("&secret=" + secret);
        return (String) JSONObject.parseObject(HttpUtil.get(sb.toString()), Map.class).get("access_token");
    }
}
