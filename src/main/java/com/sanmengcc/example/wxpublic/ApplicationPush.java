package com.sanmengcc.example.wxpublic;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.sanmengcc.example.wxpublic.ApplicationQr.JSON;

/**
 * @ClassNameApplicationPush
 * @Description
 * @Author sanmengcc
 * @Date2020/6/30 21:54
 * @Version V1.0
 **/
public class ApplicationPush {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String accessToken = ApplicationMediaUploadAndDown.getAccessToken();


    public static void main(String[] args) throws IOException {
        sendServiceStatus("Typing");
        sendServiceStatus("CancelTyping");
    }

    /**
     * @Description 设置客服输入状态
     * @Author sanmengcc
     * @Date 2020/6/30 23:15
     */
    public static void sendServiceStatus(String typing) throws IOException {
        //"Typing"：对用户下发“正在输入"状态 "CancelTyping"：取消对用户的”正在输入"状态
        JSONObject param = new JSONObject();
        param.put("touser", "sanmeng");
        param.put("command", "typing");
        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 发送小程序卡片
     * @Author  sanmengcc
     * @Date   2020/6/30 23:15
     */
    public static void sendMiniService() throws IOException {
        //发送图文消息（跳转外链）
        JSONObject miniprogram = new JSONObject();
        miniprogram.put("touser", "sanmeng");
        miniprogram.put("msgtype", "miniprogrampage");
        JSONObject miniprogrampage = new JSONObject();
        miniprogrampage.put("appid", "xxxxx");
        miniprogrampage.put("pagepath", "xxxx");
        miniprogrampage.put("thumb_media_id", "xxxx");
        miniprogram.put("miniprogrampage", miniprogrampage);
        sendService(miniprogrampage);
    }

    /**
     * @Description 跳转类客服消息发送
     * @Author  sanmengcc
     * @Date   2020/6/30 23:04
     */
    public static void sendSuperService() throws IOException {
        //发送图文消息（跳转外链）
        JSONObject news = new JSONObject();
        news.put("touser", "sanmeng");
        news.put("msgtype", "news");
        JSONObject articles = new JSONObject();
        articles.put("title", "标题");
        articles.put("description", "描述");
        articles.put("url", "url");
        articles.put("picurl", "picurl");
        news.put("articles", articles);
        sendService(news);
        //发送图文消息（跳转内部图文信息）
        JSONObject mpnews = new JSONObject();
        mpnews.put("touser", "sanmeng");
        mpnews.put("msgtype", "mpnews");
        JSONObject mpnewss = new JSONObject();
        mpnewss.put("media_id", "123456");
        mpnews.put("mpnews", mpnewss);
        sendService(mpnews);
        //发送菜单信息 当用户点击后，微信会发送一条XML消息到开发者服务器
        JSONObject menu = new JSONObject();
        menu.put("touser", "sanmeng");
        menu.put("msgtype", "msgmenu");
        JSONObject msgmenu = new JSONObject();
        msgmenu.put("head_content", "您对本次服务是否满意呢");
        List<JSONObject> list = new ArrayList<>();
        list.add(new JSONObject() {{
            put("id", "101");
            put("content", "满意");
        }});
        list.add(new JSONObject() {{
            put("id", "102");
            put("content", "不满意");
        }});
        msgmenu.put("list", list);
        msgmenu.put("tail_content", "欢迎再次光临");
        menu.put("msgmenu", mpnewss);
        sendService(menu);
    }

    /**
     * @Description 发送常规客服消息
     * @Author  sanmengcc
     * @Date   2020/6/30 23:04
     */
    public static void sendService() throws IOException {
        //发送文本消息
        JSONObject text = new JSONObject();
        text.put("touser", "sanmeng");
        text.put("msgtype", "text");
        text.put("content", "Hello World");
        sendService(text);
        //发送图片信息
        JSONObject image = new JSONObject();
        image.put("touser", "sanmeng");
        image.put("msgtype", "image");
        JSONObject images = new JSONObject();
        images.put("media_id", "123456");
        image.put("image", images);
        sendService(image);
        //发送视频信息
        JSONObject video = new JSONObject();
        video.put("touser", "sanmeng");
        video.put("msgtype", "video");
        JSONObject videos = new JSONObject();
        videos.put("media_id", "123456");
        videos.put("title", "视频");
        video.put("description", "描述");
        sendService(video);
        //发送音频信息
        JSONObject voice = new JSONObject();
        voice.put("touser", "sanmeng");
        voice.put("msgtype", "voice");
        JSONObject voices = new JSONObject();
        voices.put("media_id", "123456");
        voice.put("voice", voices);
        sendService(voice);
        //发送音乐
        JSONObject music = new JSONObject();
        music.put("touser", "sanmeng");
        music.put("msgtype", "music");
        JSONObject musics = new JSONObject();
        musics.put("title", "123456");
        musics.put("description", "123456");
        musics.put("musicurl", "123456");//音乐链接
        musics.put("hqmusicurl", "123456");//高品质音乐链接，wifi环境优先使用该链接播放音乐
        musics.put("thumb_media_id", "123456");
        music.put("music", musics);
        sendService(voice);
    }


    /**
     * @Description 发送客服消息
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void sendService(JSONObject param) throws IOException {
        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 获取客服列表
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void getCustomerServiceList() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url(url).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 修改客服
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void delCustomerService() throws IOException {
        JSONObject param = new JSONObject();
        param.put("kf_account","sanmeng");
        param.put("nickname","sanmeng");
        param.put("password", "sanmeng");
        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 修改客服
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void modifyCustomerService() throws IOException {
        JSONObject param = new JSONObject();
        param.put("kf_account","sanmeng");
        param.put("nickname","sanmeng");
        param.put("password", "sanmeng");
        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 添加客服
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void addCustomerService() throws IOException {
        JSONObject param = new JSONObject();
        param.put("kf_account","sanmeng");
        param.put("nickname","sanmeng");
        param.put("password", "sanmeng");
        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 一次性推送
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void pushOnce() throws IOException {
        JSONObject param = new JSONObject();
        param.put("touser","xxxxxxxxxxxxxxd");
        param.put("template_id","xxxxxxxxxxxxxxd");
        JSONObject miniprogram = new JSONObject();
        miniprogram.put("appid", "xxxxxxxxxx");
        miniprogram.put("pagepath", "index?foo=bar");
        param.put("miniprogram", miniprogram);
        param.put("scene","SCENE");
        param.put("title","title");
        JSONObject content = new JSONObject();
        content.put("value", "xxx");
        content.put("color", "color");
        JSONObject data = new JSONObject();
        data.put("content", content);
        param.put("data",data);

        String contentStr = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, contentStr);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 删除模版
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void delTemplate() throws IOException {
        JSONObject param = new JSONObject();
        param.put("template_id","TM00015");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 获取模版列表
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void getTemplateList() throws IOException {
        JSONObject param = new JSONObject();
        param.put("template_id_short","TM00015");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 获取模版ID
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void getTemplateId() throws IOException {
        JSONObject param = new JSONObject();
        param.put("template_id_short","TM00015");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 获取设置的行业信息
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void getIndustry() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url(url).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    /**
     * @Description 设置所属行业
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void setIndustry() throws IOException {
        JSONObject param = new JSONObject();
        param.put("industry_id1", "1");
        param.put("industry_id2", "4");
        String content = param.toJSONString();
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, content);
        Request request = (new Request.Builder()).url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * @Description 发送订阅消息
     * @Author  sanmengcc
     * @Date   2020/6/30 22:20
     */
    public static void pushTemplate(){
        String content = "{\n" +
                "\"touser\":\"${touser}\",\n" +
                "\"data\":${data},\n" +
                "\"template_id\":\"${template_id}\",\n" +
                "\"miniprogram\":{\"pagepath\":\"${pagepath}\",\"appid\":\"${appid}\"}\n" +
                "}";

        List<String> openidList = Arrays.asList("1");
        WxPushVo wxPushMsg = new WxPushVo();
        wxPushMsg.setAppid("wx5a1772c5bbd052d6");
        //wxPushMsg.setPagepath(""); 跳转小程序地址 （跳转小程序必填）
        TreeMap<String, WxTemplateData> data = new TreeMap<>();
        data.put("first", new WxTemplateData("恭喜你购买成功!", "#173177"));
        data.put("keyword1", new WxTemplateData("巧克力", "#173177"));
        wxPushMsg.setData(data);

        Optional<List<String>> listOptional = wxTemplateConversion(content, openidList, "xxxxxxx", wxPushMsg);
        if (listOptional.isPresent()) {
            String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
            OkHttpClient client = new OkHttpClient();
            listOptional.get()
                    .forEach(json->{
                        System.out.println(content);
                        RequestBody body = RequestBody.create(JSON, content);
                        Request request = (new Request.Builder()).url(url).post(body).build();
                        Response response = null;
                        try {
                            response = client.newCall(request).execute();
                            String result = response.body().string();
                            System.out.println(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    /**
     * @Description 参数转换
     * @Author  sanmengcc
     * @Date   2020/6/30 22:01
     */
    public static Optional<List<String>> wxTemplateConversion(String content,List<String> openIdList,String templateId, WxPushVo wxPushMsg){
        return Optional.ofNullable(openIdList)
                .map(idList ->
                        idList.stream()
                                .map(openid -> {
                                    //替换模板参数
                                    String postStr = content;
                                    //模板ID
                                    postStr = postStr.replace("${template_id}", templateId);
                                    //微信小程序appid
                                    postStr = postStr.replace("${appid}", wxPushMsg.getAppid());
                                    //微信公众号用户openid
                                    postStr = postStr.replace("${touser}", openid);
                                    //微信小程序内部地址
                                    postStr = postStr.replace("${pagepath}", wxPushMsg.getPagepath());
                                    //推送参数
                                    postStr = postStr.replace("${data}", JSONObject.toJSONString(wxPushMsg.getData()));
                                    return postStr;
                                }).collect(Collectors.toList())
                );
    }

}
