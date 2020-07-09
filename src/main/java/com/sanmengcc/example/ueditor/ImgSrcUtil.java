package com.sanmengcc.example.ueditor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassNameImgSrcUtil
 * @Description
 * @Author sanmengcc
 * @Date2020/7/9 13:40
 * @Version V1.0
 **/
public class ImgSrcUtil {

    public static List<String> getImgSrc(String htmlStr) {
        String img = "";
        Pattern imgPattern;
        Matcher imgMatcher;
        List<String> pics = new ArrayList<>();
        String imgReg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        imgPattern = Pattern.compile(imgReg, Pattern.CASE_INSENSITIVE);
        imgMatcher = imgPattern.matcher(htmlStr);
        while (imgMatcher.find()) {
            img = img + "," + imgMatcher.group();
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String htmlStr = "<p style=\"text-indent:43px\"><span style=\"font-size:21px;font-family:仿宋_GB2312\">为认真贯彻落实习近平总书记关于疫情防控工作的重要指示精神，根据国务院应对新型冠状病毒感染肺炎疫情联防联控机制和省人民政府关于做好疫情防控期间企业复工复产的工作部署，2月26日，由镇党委副书记、驻村领导梁冠伦，驻村组长黄锦威等领导，村委副书记谢志平及村企业管理人员检查走访复工四上企业。</span></p><p style=\"text-indent:43px\"><span style=\"font-size:21px;font-family:仿宋_GB2312\"><br/> &nbsp;&nbsp; &nbsp;检查中就各企业在复工复产中对员工的返程和健康状况、每日是否测体温、员工上班戴口罩，勤通风、间隔用餐、当前所遇到的困难等情况作出了详细了解。</span></p><p style=\"text-indent:43px\"><span style=\"font-size:21px;font-family:仿宋_GB2312\"><br/> </span><span style=\"font-size:21px\">&nbsp;</span><span style=\"font-size:21px;font-family:仿宋_GB2312\"> &nbsp;</span><span style=\"font-size:21px;font-family:仿宋_GB2312\">相信在各方共同努力下一定能打赢这场战役，谢家庄企业办将竭尽所能、尽职尽责，不负众望，继续为企业给与支持和帮助。</span></p><p style=\"text-indent:43px\"><span style=\"font-size:21px;font-family:仿宋_GB2312\"><br/></span></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230842805000.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0ca670b0-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image002.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230846555000.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0cac6420-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image004.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230853117500.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0ca3ffb0-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image006.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230855617500.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0ca14090-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image008.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230858586250.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0c9ad7f0-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image010.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230861086250.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0ca97df0-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image012.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230863586250.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0cafe690-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image014.jpg\"/></p><p style=\"text-align: center;\"><img src=\"http://taihe.client.huiz.cn/upload/202005/org/132345230990305000.jpg\" alt=\"https://ss2.meipian.me/users/52060712/0c9d9710-586f-11ea-8e99-33718853231d.jpg?imageView2/2/w/750/h/1400/q/80\" title=\"clip_image016.jpg\"/></p>";
        System.out.println(getImgSrc(htmlStr));
    }

}
