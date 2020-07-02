package com.sanmengcc.example.wxapplet;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.util.TreeMap;

/**
 * @Description
 * @Author sanmengcc
 * @Date 2020/7/2 9:09
 */
@Data
@Builder
public class WxMiniPushMsg {


    /**
     * 对应小程序页面
     */
    private String page;

    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 小程序用户openid
     */
    private String touser;

    /**
     * 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
     */
    @Builder.Default
    private String lang = "zh_CN";

    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    @Builder.Default
    private String miniprogram_state = "formal";

    /**
     * 微信推送消息内容
     */
    private TreeMap<String, WxAppletTemplateData> data;

    /**
     * 获取推送JSON
     *
     * @return
     */
    public String getPushJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", this.page);
        jsonObject.put("template_id", this.template_id);
        jsonObject.put("touser", this.touser);
        jsonObject.put("lang", this.lang);
        jsonObject.put("miniprogram_state", this.miniprogram_state);
        jsonObject.put("data", JSONObject.toJSONString(this.data));
        return jsonObject.toJSONString();
    }

}
