package com.sanmengcc.example.wxapplet;

import com.alibaba.fastjson.JSONObject;
import com.sanmengcc.example.util.FileUtil;
import com.sanmengcc.example.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassNameApplication
 * @Description 微信小程序Token相关接口
 * @Author sanmengcc
 * @Date2020/6/30 21:27
 * @Version V1.0
 **/
public class ApplicationMessage {

    public static String accessToken = "34_WZNuC7znrhm5_oDRQf6JlA1ABn5JQa5vAAcrYowb_oJMbC0wuR5NxuKPyBx3FdUV9CmIf4QsnElwj-jaZIUpFtsjrMAqT0edKZzcNuO8GtYO587FeAeS0LF9GTHe_vlF4ZyYaIzLJQCqO-KaGTPaADAMZV";

    private final static String appid = "xxxxxxxxxxxx";

    private final static String secret = "xxxxxxxxxxx";


    public static void main(String[] args) throws IOException {
        updateActivityForm();
    }

    /**
     * @Description 修改动态消息
     * @Author  sanmengc
     * @Date   2020/7/3 8:50
     */
    public static void updateActivityForm() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/updatablemsg/send?access_token=" + accessToken;

        JSONObject param = new JSONObject();
        param.put("activity_id", "sanmengcc");
        param.put("target_state", "0");

        JSONObject template_info = new JSONObject();
        List<JSONObject> parameter_list = Arrays.asList(new JSONObject() {{
            put("name", "sanmenggc");//注意一下参数的格式String 还是Number
            put("value", "sanmenggc");
        }});
        template_info.put("parameter_list", parameter_list);

        HttpUtil.get(url);
    }

    /**
     * @Description 创建动态消息
     * @Author  sanmengc
     * @Date   2020/7/3 8:50
     */
    public static void createActivityForm() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create?access_token=" + accessToken;
        HttpUtil.get(url);
    }

    /**
     * @Description 下发小程序和公众号统一的服务消息
     * @Author  sanmengcc
     * @Date   2020/7/3 8:34
     */
    public static void uniform_send() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=" + accessToken;
        JSONObject param = new JSONObject();
        param.put("touser", "sanmengccc");

        //小程序结构体
        JSONObject weapp_template_msg = new JSONObject();
        weapp_template_msg.put("template_id", "sanmengctid");
        weapp_template_msg.put("page", "page/page/index");
        weapp_template_msg.put("form_id", "SHIHASIDHASIDHIOAS");

        JSONObject weapp_template_msg_data = new JSONObject();
        weapp_template_msg_data.put("keyword1", new JSONObject(){{
            put("value", "339208499");}});
        weapp_template_msg.put("data", weapp_template_msg_data);
        weapp_template_msg.put("emphasis_keyword", "sanmengccc");
        param.put("weapp_template_msg", weapp_template_msg);

        //公众号结构体
        JSONObject mp_template_msg = new JSONObject();
        mp_template_msg.put("appid", "sanmengcc");
        mp_template_msg.put("url", "https://www.baidu.com");
        mp_template_msg.put("template_id", "SHIHASIDHASIDHIOAS");
        JSONObject miniprogram = new JSONObject();
        miniprogram.put("appid", "sanmengcc");
        miniprogram.put("pagepath", "/page/index");
        mp_template_msg.put("miniprogram", miniprogram);

        JSONObject mp_template_msg_data = new JSONObject();
        mp_template_msg_data.put("first", new JSONObject() {{
            put("value", "恭喜你购买成功");
            put("color", "#173177");
        }});
        mp_template_msg.put("data", mp_template_msg_data);

        HttpUtil.post(url, param.toJSONString());

    }

    /**
     * @Description 发送订阅消息
     * @Author  sanmengcc
     * @Date   2020/7/2 9:34
     */
    public static void sendMessage() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        String content = "{\"touser\":\"${touser}\",\"data\":${data},\"template_id\":\"${template_id}\",\"page\":\"${page}\",\"lang\":\"${lang}\",\"miniprogram_state\":\"${miniprogram_state}\"}";
        TreeMap<String, WxAppletTemplateData> data = new TreeMap<>();
        data.put("thing1", new WxAppletTemplateData("实名认证通过"));
        data.put("date2", new WxAppletTemplateData("2020-07-02"));
        HttpUtil.post(url, wxTemplateConversion(content, WxMiniPushMsg.builder()
                .data(data)
                .page("/page/index")
                .touser("sanmengcc")
                .template_id("xxxxxxxxxxxx")
                .build()));
    }

    /**
     * @Description 订阅消息参数转换
     * @Author sanmengc
     * @Date 2020/7/2 9:18
     */
    public static String wxTemplateConversion(String content, WxMiniPushMsg wxPushMsg) {
        //替换模板参数(暂时保留格式与公众号一致)
        String postStr = content;
        //模板ID
        postStr = postStr.replace("${template_id}", wxPushMsg.getTemplate_id());
        //微信公众号用户openid
        postStr = postStr.replace("${touser}", wxPushMsg.getTouser());
        //微信小程序内部地址
        postStr = postStr.replace("${page}", wxPushMsg.getPage());
        //进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
        postStr = postStr.replace("${lang}", wxPushMsg.getLang());
        //跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
        postStr = postStr.replace("${miniprogram_state}", wxPushMsg.getMiniprogram_state());
        //推送参数
        postStr = postStr.replace("${data}", JSONObject.toJSONString(wxPushMsg.getData()));
        System.out.println("postStr = " + postStr);
        return postStr;
    }

    /**
     * @Description 删除订阅消息模板
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void deltemplate() throws IOException {
        JSONObject param = new JSONObject();
        param.put("priTmplId", "xxxxxxxxxx");
        HttpUtil.post("https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate?access_token=" + accessToken, param.toJSONString());
    }

    /**
     * @Description 增加订阅消息模板
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void addtemplate() throws IOException {
        JSONObject param = new JSONObject();
        param.put("tid", "xxxxxxxxxx");
        param.put("kidList", Arrays.asList(1, 2, 3, 4, 5));
        param.put("sceneDesc", "描述");
        HttpUtil.post("https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=" + accessToken, param.toJSONString());
    }

    /**
     * @Description 获取模板标题下的关键词列表
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void getpubtemplatekeywords() throws IOException {
        HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords?access_token="
                + accessToken
                + "&tid=xxxxxxxxxxxxxx");
    }

    /**
     * @Description 获取帐号所属类目下的公共模板标题
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void getpubtemplatetitles() throws IOException {
        HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles?access_token="
                + accessToken
                + "&ids=1,2,3&start=0&limit=30");
    }

    /**
     * @Description 获取订阅消息小程序账号的类目
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void getcategory() throws IOException {
        HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/getcategory?access_token=" + accessToken);
    }

    /**
     * @Description 获取订阅消息模版列表
     * @Author  sanmengcc
     * @Date   2020/7/2 8:54
     */
    public static void getSubscriptionTemplate() throws IOException {
        HttpUtil.get("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=" + accessToken);
    }

    /**
     * @Description 发送客服消息
     * @Author  sanmengcc
     * @Date   2020/7/2 8:46
     */
    public static void sendServiceMessage() throws IOException {
        //文字消息
        JSONObject param = new JSONObject();
        param.put("touser", "xxxxxxxxxxxxxx");
        param.put("msgtype", "text");
        JSONObject text = new JSONObject();
        text.put("content", "文字消息");
        param.put("text", text);
        sendServiceMessage(param);

        //图片信息
        param.put("msgtype", "image");
        JSONObject image = new JSONObject();
        image.put("media_id", "VYAvYM9V6w82QQ5zzkgCSG-H8uOWAuN0jJBc9l8uPuPokNoi-ZrUtIBoKc6iMEmZ");
        param.put("image", image);
        param.remove("text");
        sendServiceMessage(param);

        //图文链接
        param.put("msgtype", "link");
        JSONObject link = new JSONObject();
        link.put("title", "标题");
        link.put("description", "描述");
        link.put("url", "图文链接消息被点击后跳转的链接");
        link.put("thumb_url", "图文链接消息的图片链接，支持 JPG、PNG 格式，较好的效果为大图 640 X 320，小图 80 X 80");
        param.put("link", link);
        param.remove("image");
        sendServiceMessage(param);

        //小程序卡片
        param.put("msgtype", "miniprogrampage");
        JSONObject miniprogrampage  = new JSONObject();
        miniprogrampage.put("title", "标题");
        miniprogrampage.put("pagepath", "小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar");
        miniprogrampage.put("thumbMediaId", "小程序消息卡片的封面， image 类型的 media_id，通过 新增素材接口 上传图片文件获得，建议大小为 520*416");
        param.put("miniprogrampage", miniprogrampage);
        param.remove("link");
        sendServiceMessage(param);
    }

    /**
     * @Description 发送客服消息
     * @Author sanmengcc
     * @Date 2020/7/2 8:36
     */
    public static void sendServiceMessage(JSONObject param) throws IOException {
        HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken, param.toJSONString());
    }

    /**
     * @Description 设置客服回复消息状态
     * @Author  sanmengcc
     * @Date   2020/7/2 8:36
     */
    public static void setTyping() throws IOException {
        JSONObject param = new JSONObject();
        param.put("touser", "xxxxxxxxxxxxxx");
        //Typing	对用户下发"正在输入"状态	 CancelTyping	取消对用户的"正在输入"状态
        param.put("command", "Typing");
        HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=" + accessToken, param.toJSONString());
    }

    /**
     * @Description 获取客服消息图片(只能返回buffer)
     * @Author  sanmengcc
     * @Date   2020/7/2 8:25
     */
    public static void getMedia(String media_id) throws IOException {
        URL url =new URL("https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + media_id);
        InputStream is = url.openStream();
        File file = new File("D:\\sanmeng001.png");
        //不存在,创建必要的(多层)目录
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[2048];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        if (fos != null) {
            fos.close();
        }
        if (is != null) {
            is.close();
        }
    }

    /**
     * @Description 客服消息上传图片
     * @Author  sanmengcc
     * @Date   2020/7/2 8:23
     */
    public static void uploadMedia() throws IOException {
        uploadFile("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=image",
                "image", FileUtil.getMultipartEntity(new File("D:\\123.png")));
    }

    private final static String BOUNDARY_STR = "------------7da2e536604c8";

    /**
     * @Description 文件上传
     * @Author  sanmengc
     * @Date   2020/7/2 8:22
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
        System.out.println("result=" + result);
        EntityUtils.consume(entity);
        return result;
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
