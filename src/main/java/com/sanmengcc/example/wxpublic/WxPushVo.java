package com.sanmengcc.example.wxpublic;

import lombok.Data;

import java.util.List;
import java.util.TreeMap;

/**
 * @ClassNameWxPushVo
 * @Description
 * @Author sanmengcc
 * @Date2020/6/30 21:48
 * @Version V1.0
 **/
@Data
public class WxPushVo {

    /**
     * 跳转小程序地址 （跳转小程序必填）
     */
    private String pagepath = "";

    /**
     * 跳转小程序的appid (跳转小程序必填）
     */
    private String appid = "";

    /**
     * 推送对象（需要为微信公众号用户openid）
     */
    private List<String> openidList;

    /**
     * 微信推送消息内容
     */
    private TreeMap<String, WxTemplateData> data;


}
