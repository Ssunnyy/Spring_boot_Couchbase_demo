package com.nearsen_enterprise.Config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.N1qlQueryResult;
import org.springframework.beans.factory.annotation.Value;

// 后端会议接口
public class ConstantsMeetingInterface {

    public static final int version = 2;// 版本

    //    private static final Boolean SERVER_RELEASE = true;
    public static final Boolean SERVER_RELEASE = false;

    public static final String dev_prefix = "http://dev.nearsen.cn:8080/nearsen/";
    public static final String prod_prefix = "http://api.nearsen.cn:8080/nearsen/";
    public static final String meetingBucket = "nearinfo";

    // <version v1>
    /*
     * 校验会议地址经纬度
     */
    public static final String meeting_checkloction_endpoint = "v1/meeting/checkloction";
    /*
     * 获取当前软件版本信息
     */
    public static final String meeting_version_endpoint = "v1/selectmeeting/ver";

    // <version v2>
    /*
     * 创建会议
     */
    public static final String meeting_select_endpoint = "v2/meeting/createseleted";
    /*
     * 会议投放
     */
    public static final String meeting_tovip_endpoint = "v2/meeting/pubtovip";


}
