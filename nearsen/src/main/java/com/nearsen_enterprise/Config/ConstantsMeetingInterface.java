package com.nearsen_enterprise.Config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.N1qlQueryResult;

// 后端会议接口
public class ConstantsMeetingInterface {


    public static final int version = 2;// 版本

//    private static final Boolean SERVER_RELEASE = true;
    public static final Boolean SERVER_RELEASE = false;
    // 服务器配置
    public static final String ip = "bt01.nearsen.cn";
    public static String bucketname = "nearinfo";


    public static final String dev_prefix = "http://dev.nearsen.cn:8080/nearsen/";
    public static final String prod_prefix = "http://api.nearsen.cn:8080/nearsen/";

    public static final String meeting_select_endpoint = "v2/meeting/createseleted";
    public static final String meeting_tovip_endpoint = "v2/meeting/pubtovip";
    public static final String meeting_version_endpoint = "v1/selectmeeting/ver";


}
