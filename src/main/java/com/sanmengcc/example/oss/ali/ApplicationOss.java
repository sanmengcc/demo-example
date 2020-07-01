package com.sanmengcc.example.oss.ali;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.*;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

/**
 * @ClassNameApplicationOss
 * @Description OSS文件服务
 * @Author sanemngcc
 * @Date2020/6/29 19:34
 * @Version V1.0
 **/
public class ApplicationOss {

    final static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    final static String accessKeyId = "LTAIMCfqGfSBdgum";
    final static String accessKeySecret = "DlY6n13a2FUmS435a1S4ODgajKC4UK";


    public static void main(String[] args) throws Exception {
        stringUpload();
    }


    /**
     * @Description 简单上传
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void stringUpload() throws IOException {
        OSS ossClient = getOssClient();
        String content = "Hello OSS";
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest("mysameng", "test/202006001", new ByteArrayInputStream(content.getBytes()));
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传字符串。
        ossClient.putObject(putObjectRequest);

        // Byte数组
        byte[] content2 = "Hello OSS".getBytes();
        ossClient.putObject("mysameng", "test/202006002", new ByteArrayInputStream(content2));

        //InputStream
        InputStream inputStream = new FileInputStream(new File("d:\\excel.xlsx"));
        ossClient.putObject("mysameng", "test/202006001", inputStream);

        //URL
        InputStream inputStream2 = new URL("https://img-blog.csdn.net/20180529103049209").openStream();
        ossClient.putObject("mysameng", "test/20200629",inputStream2);

        //File
        PutObjectRequest putObjectRequest2 = new PutObjectRequest("mysameng", "test/202006002", new File("d:\\excel.xlsx"));
        ossClient.putObject(putObjectRequest2);
        shutdownOss(ossClient);
    }

    /**
     * @Description 关闭访问日志设置
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void closeLogSetting() throws IOException {
        OSS ossClient = getOssClient();
        SetBucketLoggingRequest request = new SetBucketLoggingRequest("mysameng");
        request.setTargetBucket(null);
        request.setTargetPrefix(null);
        ossClient.setBucketLogging(request);
        shutdownOss(ossClient);
    }

    /**
     * @Description 查看访问日志设置
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void getLogSetting() throws IOException {
        OSS ossClient = getOssClient();
        BucketLoggingResult result = ossClient.getBucketLogging("mysameng");
        System.out.println(result.getTargetBucket());
        System.out.println(result.getTargetPrefix());
        shutdownOss(ossClient);
    }

    /**
     * @Description 开启访问日志
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void startLog() throws IOException {
        OSS ossClient = getOssClient();
        SetBucketLoggingRequest request = new SetBucketLoggingRequest("mysameng");
        // 设置存放日志文件的存储空间
        request.setTargetBucket("mysameng");
        // 设置日志文件存放的目录
        request.setTargetPrefix("log");
        ossClient.setBucketLogging(request);
        shutdownOss(ossClient);
    }

    /**
     * @Description 防盗链
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void refererList() throws IOException {
        OSS ossClient = getOssClient();
        List<String> refererList = new ArrayList<String>();
        // 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）
        refererList.add("http://www.aliyun.com");
        refererList.add("http://www.*.com");
        refererList.add("http://www.?.aliyuncs.com");
        // 设置存储空间Referer列表。设为true表示Referer字段允许为空
        BucketReferer br = new BucketReferer(true, refererList);
        ossClient.setBucketReferer("mysameng", br);

        shutdownOss(ossClient);
    }

    /**
     * @Description 第三方付费访问Object
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void paidAccess() throws IOException {
        OSS ossClient = getOssClient();
        Payer payer = Payer.Requester;
        // putObject接口指定付费者
        String content = "hello";
        PutObjectRequest putObjectRequest = new PutObjectRequest("mysameng", "objectName", new ByteArrayInputStream(content.getBytes()));
        putObjectRequest.setRequestPayer(payer);
        ossClient.putObject(putObjectRequest);

        // getObject接口指定付费者
        GetObjectRequest getObjectRequest = new GetObjectRequest("mysameng", "objectName");
        getObjectRequest.setRequestPayer(payer);
        OSSObject ossObject = ossClient.getObject(getObjectRequest);
        ossObject.close();

        // deleteObject接口指定付费者
        GenericRequest genericRequest = new GenericRequest("mysameng", "objectName");
        genericRequest.setRequestPayer(payer);
        ossClient.deleteObject(genericRequest);
        shutdownOss(ossClient);
    }

    /**
     * @Description 获取请求者付费模式配置
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void getUpRequesterPaymentMode() throws ParseException {
        OSS ossClient = getOssClient();
        // 获取请求者付费模式
        GetBucketRequestPaymentResult result = ossClient.getBucketRequestPayment("mysameng");
        System.out.println("Payer:" + result.getPayer());
        shutdownOss(ossClient);
    }

    /**
     * @Description 设置请求者付费模式
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void setUpRequesterPaymentMode() throws ParseException {
        OSS ossClient = getOssClient();
        // 设置请求者付费模式
        Payer payer = Payer.Requester;
        ossClient.setBucketRequestPayment("mysameng", payer);
        shutdownOss(ossClient);
    }

    /**
     * @Description 清空生命周期规则
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void cleanBucketObjectRole() throws ParseException {
        OSS client = getOssClient();
        client.deleteBucketLifecycle("mysameng");
        shutdownOss(client);
    }

    /**
     * @Description 获取生命周期
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void getBucketObjectRole() throws ParseException {
        OSS client = getOssClient();
        // 获取生命周期规则
        List<LifecycleRule> rules = client.getBucketLifecycle("mysameng");

        // 查看生命周期规则
        for (LifecycleRule r : rules) {
            System.out.println("================");
            // 查看规则id
            System.out.println("rule id: " + r.getId());
            // 查看规则状态
            System.out.println("rule status: " + r.getStatus());
            // 查看规则前缀
            System.out.println("rule prefix: " + r.getPrefix());
            // 查看规则标签
            if (r.hasTags()) {
                System.out.println("rule tagging: " + r.getTags().toString());
            }
            // 查看过期天数规则
            if (r.hasExpirationDays()) {
                System.out.println("rule expiration days: " + r.getExpirationDays());
            }
            // 查看过期日期规则
            if (r.hasCreatedBeforeDate()) {
                System.out.println("rule expiration create before days: " + r.getCreatedBeforeDate());
            }
            // 查看过期分片规则
            if (r.hasAbortMultipartUpload()) {
                if (r.getAbortMultipartUpload().hasExpirationDays()) {
                    System.out.println("rule abort uppart days: " + r.getAbortMultipartUpload().getExpirationDays());
                }
                if (r.getAbortMultipartUpload().hasCreatedBeforeDate()) {
                    System.out.println("rule abort uppart create before date: " + r.getAbortMultipartUpload().getCreatedBeforeDate());
                }
            }
            // 查看存储类型转换规则
            if (r.getStorageTransition().size() > 0) {
                for (LifecycleRule.StorageTransition transition : r.getStorageTransition()) {
                    if (transition.hasExpirationDays()) {
                        System.out.println("rule storage trans days: " + transition.getExpirationDays() +
                                " trans storage class: " + transition.getStorageClass());
                    }
                    if (transition.hasCreatedBeforeDate()) {
                        System.out.println("rule storage trans before create date: " + transition.getCreatedBeforeDate());
                    }
                }
            }

            // 查看是否自动删除过期删除标记
            if (r.hasExpiredDeleteMarker()) {
                System.out.println("rule expired delete marker: " + r.getExpiredDeleteMarker());
            }
            // 查看非当前版本Object存储类型转换规则
            if (r.hasNoncurrentVersionStorageTransitions()) {
                for (LifecycleRule.NoncurrentVersionStorageTransition transition : r.getNoncurrentVersionStorageTransitions()) {
                    System.out.println("rule noncurrent versions trans days:" + transition.getNoncurrentDays() +
                            " trans storage class: " + transition.getStorageClass());
                }
            }
            // 查看非当前版本Object过期规则。
            if (r.hasNoncurrentVersionExpiration()) {
                System.out.println("rule noncurrent versions expiration days:" + r.getNoncurrentVersionExpiration().getNoncurrentDays());
            }
        }
        shutdownOss(client);
    }

    /**
     * @Description 设置生命周期
     * @Author sanmengcc
     * @Date 2020/6/29 21:21
     */
    public static void setBucketObjectRole() throws ParseException {
        OSS client = getOssClient();
        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest("mysameng");

        // 设置规则ID、文件匹配前缀与标签
        String ruleId0 = "rule0";
        String matchPrefix0 = "A0/";
        Map<String, String> matchTags0 = new HashMap<String, String>();
        matchTags0.put("key0", "value0");


        String ruleId1 = "rule1";
        String matchPrefix1 = "A1/";
        Map<String, String> matchTags1 = new HashMap<String, String>();
        matchTags1.put("key1", "value1");

        String ruleId2 = "rule2";
        String matchPrefix2 = "A2/";

        String ruleId3 = "rule3";
        String matchPrefix3 = "A3/";

        String ruleId4 = "rule4";
        String matchPrefix4 = "A4/";

        String ruleId5 = "rule5";
        String matchPrefix5 = "A5/";

        String ruleId6 = "rule6";
        String matchPrefix6 = "A6/";

        // 距最后修改时间3天后过期
        LifecycleRule rule = new LifecycleRule(ruleId0, matchPrefix0, LifecycleRule.RuleStatus.Enabled, 3);
        rule.setTags(matchTags0);
        request.AddLifecycleRule(rule);

        // 指定日期之前创建的文件过期
        rule = new LifecycleRule(ruleId1, matchPrefix1, LifecycleRule.RuleStatus.Enabled);
        rule.setCreatedBeforeDate(DateUtil.parseIso8601Date("2022-10-12T00:00:00.000Z"));
        rule.setTags(matchTags1);
        request.AddLifecycleRule(rule);

        // 分片3天后过期
        rule = new LifecycleRule(ruleId2, matchPrefix2, LifecycleRule.RuleStatus.Enabled);
        LifecycleRule.AbortMultipartUpload abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setExpirationDays(3);
        rule.setAbortMultipartUpload(abortMultipartUpload);
        request.AddLifecycleRule(rule);

        // 指定日期之前的分片过期
        rule = new LifecycleRule(ruleId3, matchPrefix3, LifecycleRule.RuleStatus.Enabled);
        abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setCreatedBeforeDate(DateUtil.parseIso8601Date("2022-10-12T00:00:00.000Z"));
        rule.setAbortMultipartUpload(abortMultipartUpload);
        request.AddLifecycleRule(rule);

        // 距最后修改时间10天后转低频访问存储类型，距最后修改时间30天后转归档存储类型
        rule = new LifecycleRule(ruleId4, matchPrefix4, LifecycleRule.RuleStatus.Enabled);
        List<LifecycleRule.StorageTransition> storageTransitions = new ArrayList<LifecycleRule.StorageTransition>();
        LifecycleRule.StorageTransition storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setStorageClass(StorageClass.IA);
        storageTransition.setExpirationDays(10);
        storageTransitions.add(storageTransition);
        storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setStorageClass(StorageClass.Archive);
        storageTransition.setExpirationDays(30);
        storageTransitions.add(storageTransition);
        rule.setStorageTransition(storageTransitions);
        request.AddLifecycleRule(rule);

        // 指定最后修改日期在2022-10-12之前的文件转为归档存储
        rule = new LifecycleRule(ruleId5, matchPrefix5, LifecycleRule.RuleStatus.Enabled);
        storageTransitions = new ArrayList<LifecycleRule.StorageTransition>();
        storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setCreatedBeforeDate(DateUtil.parseIso8601Date("2022-10-12T00:00:00.000Z"));
        storageTransition.setStorageClass(StorageClass.Archive);
        storageTransitions.add(storageTransition);
        rule.setStorageTransition(storageTransitions);
        request.AddLifecycleRule(rule);

        // rule6针对版本控制状态下的Bucket
        rule = new LifecycleRule(ruleId6, matchPrefix6, LifecycleRule.RuleStatus.Enabled);
        // 设置Object相对最后修改时间365天之后自动转为归档文件
        storageTransitions = new ArrayList<LifecycleRule.StorageTransition>();
        storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setStorageClass(StorageClass.Archive);
        storageTransition.setExpirationDays(365);
        storageTransitions.add(storageTransition);
        rule.setStorageTransition(storageTransitions);
        // 设置自动移除过期删除标记。
        rule.setExpiredDeleteMarker(true);
        // 设置非当前版本的object距最后修改时间10天之后转为低频访问类型。
        LifecycleRule.NoncurrentVersionStorageTransition noncurrentVersionStorageTransition =
                new LifecycleRule.NoncurrentVersionStorageTransition().withNoncurrentDays(10).withStrorageClass(StorageClass.IA);
        // 设置非当前版本的Object距最后修改时间20天之后转为归档类型
        LifecycleRule.NoncurrentVersionStorageTransition noncurrentVersionStorageTransition2 =
                new LifecycleRule.NoncurrentVersionStorageTransition().withNoncurrentDays(20).withStrorageClass(StorageClass.Archive);
        // 设置非当前版本Object 30天后删除
        LifecycleRule.NoncurrentVersionExpiration noncurrentVersionExpiration = new LifecycleRule.NoncurrentVersionExpiration().withNoncurrentDays(30);
        List<LifecycleRule.NoncurrentVersionStorageTransition> noncurrentVersionStorageTransitions = new ArrayList<LifecycleRule.NoncurrentVersionStorageTransition>();
        noncurrentVersionStorageTransitions.add(noncurrentVersionStorageTransition2);
        rule.setStorageTransition(storageTransitions);
        rule.setNoncurrentVersionExpiration(noncurrentVersionExpiration);
        rule.setNoncurrentVersionStorageTransitions(noncurrentVersionStorageTransitions);
        request.AddLifecycleRule(rule);
        shutdownOss(client);
    }

    /**
     * @Description 获取bucket Policy
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void getBucketPolicy() {
        OSS ossClient = getOssClient();
        // 获取授权策略
        GetBucketPolicyResult result = ossClient.getBucketPolicy("mysameng");
        System.out.println("policyText:" + result.getPolicyText());
        shutdownOss(ossClient);
    }

    /**
     * @Description 设置bucket Policy
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void setBucketPolicy() {
        OSS ossClient = getOssClient();
        String policyText = "{\"Statement\": [{\"Effect\": \"Allow\"," +
                " \"Action\": [\"oss:GetObject\", \"oss:ListObjects\"], " +
                "\"Resource\": [\"acs:oss:*:*:*/user1/*\"]}]," +
                " \"Version\": \"1\"}";
        ossClient.setBucketPolicy("mysameng", policyText);
        shutdownOss(ossClient);
    }

    /**
     * @Description 设置bucket标签
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void setBucketTag() {
        OSS ossClient = getOssClient();
        SetBucketTaggingRequest request = new SetBucketTaggingRequest("mysameng");
        request.setTag("mysanmeng_key", "mysanmeng_value");
        request.setTag("mysanmeng_key1", "mysanmeng_value1");
        ossClient.setBucketTagging(request);
        shutdownOss(ossClient);
    }

    /**
     * @Description 设置bucket权限
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void setBucketAccessControl() {
        OSS ossClient = getOssClient();
        ossClient.setBucketAcl("mysameng", CannedAccessControlList.Private);
        shutdownOss(ossClient);
    }

    /**
     * @Description 删除bucket
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void delBucket() {
        //The bucket you are attempting to access must be addressed using the specified endpoint. Please send all future requests to this endpoint
        OSS ossClient = getOssClient();
        ossClient.deleteBucket("mysameng");
        shutdownOss(ossClient);
    }

    /**
     * @Description 获取bucket的详细信息
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void getBucketInfo() {
        OSS ossClient = getOssClient();
        //地域信息
        String location = ossClient.getBucketLocation("mysameng");
        System.out.println("地域信息 :" + location);
        BucketInfo info = ossClient.getBucketInfo("mysameng");
        // 获取地域
        info.getBucket().getLocation();
        // 获取创建日期
        info.getBucket().getCreationDate();
        // 获取拥有者信息
        info.getBucket().getOwner();
        // 获取权限信息
        info.getGrants();
        // 获取容灾类型
        info.getDataRedundancyType();
        System.out.println("地域:" + info.getBucket().getLocation()
                + " 创建日期：" + info.getBucket().getCreationDate()
                + " 拥有者信息：" + info.getBucket().getOwner()
                + " 容灾类型：" + info.getDataRedundancyType()
                + " 权限信息：" + info.getGrants()
        );
        // 获取存储空间的访问权限
        AccessControlList acl = ossClient.getBucketAcl("mysameng");
        System.out.println("访问权限:" + acl.toString());

        // 获取Bucket标签信息。
        TagSet tagSet = ossClient.getBucketTagging(new GenericRequest("mysameng"));
        Map<String, String> tags = tagSet.getAllTags();
        System.out.println("tag :" + tags);
        shutdownOss(ossClient);
    }

    /**
     * @Description 判断bucket是否存在
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void isBucketExist() {
        OSS ossClient = getOssClient();
        Optional.ofNullable(ossClient.doesBucketExist("mysameng"))
                .ifPresent(System.out::println);
        shutdownOss(ossClient);
    }

    /**
     * @Description 获取bucket列表
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void getBucketList() {
        OSS ossClient = getOssClient();
        List<Bucket> buckets = ossClient.listBuckets();
        buckets.forEach(System.out::println);
        shutdownOss(ossClient);
    }

    /**
     * @Description创建bucket 1.注意bucketName长度
     * 2。异常信息记得try
     * 3.bucketName唯一性
     * @Author sanmengcc
     * @Date 2020/6/29 20:04
     */
    public static void creatBucket() {
        OSS ossClient = getOssClient();
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("mysameng");
        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
        // 此处以设置存储空间的存储类型为标准存储为例
        // createBucketRequest.setStorageClass(StorageClass.Standard);
        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)
        // 设置存储空间的权限为公共读，默认是私有
        createBucketRequest.setCannedACL(CannedAccessControlList.Private);
        // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
        createBucketRequest.setStorageClass(StorageClass.IA);
        // 创建存储空间 出现异常记得try cache
        Bucket bucket = ossClient.createBucket(createBucketRequest);
        System.out.println(bucket);
        shutdownOss(ossClient);
    }

    /**
     * @Description 获取ossClient
     * @Author sanmengcc
     * @Date 2020/6/29 19:46
     */
    public static OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * @Description 关闭ossClient
     * @Author sanmengcc
     * @Date 2020/6/29 19:46
     */
    public static void shutdownOss(OSS ossClient) {
        ossClient.shutdown();
    }
}
