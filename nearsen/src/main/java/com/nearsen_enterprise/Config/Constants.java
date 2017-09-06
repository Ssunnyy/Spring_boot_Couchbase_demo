package com.nearsen_enterprise.Config;

public class Constants {

    public static final String PERSON_GEO = "PersonGeo";
    public static final String NEAR_PERSON = "NearPerson:hash";
    public static final String NEAR_PERSON_INDEX = "NearPerson:hashIndex";

    public static final String USER_NEWEST_LOC = "UserNewestLoc:hash";

    public static final String NEARPERSON_SNAPSHOT_PREFIX = "npSnapshot_";
    public static final String NEARCARD_SNAPSHOT_PREFIX = "ncSnapshot_";

    public static final String ALL_PERSONS = "allPersons";
    public static final String ALL_CARDS = "allCards";

    public static final String GLOBAL_SPEAKER_LIST = "GlobalSpeakerList";
    // 全局VIP列表，不受时间空间限制的VIP信息投放使用
    public static final String GLOBAL_VIP_LIST = "GlobalVipList";
    // 全局MODEL用户列表，不受时间空间限制的MODEL信息投放使用
    public static final String GLOBAL_MODEL_LIST = "GlobalModelList";
    public static final String GLOBAL_BiBi_LIST = "GlobalBiBiList";
    public static final String GLOBAL_SPECIAL_HASH = "GlobalSpecialHash";


    // 主讲人geo列表，不同主讲人不同会议投放使用
    public static final String SPEAKER_GEO = "SpeakerGeo";
    public static final String SPEAKER_GEO_ARRAY = "SpeakerGeoArray";
    public static final String SPEAKER_GEO_ARRAY_INDEX = "SpeakerGeoArrayIndex";

    // 主讲人静态列表，该列表测试时候使用
    public static final String SPEAKER_LIST = "SpeakerList";
    public static final String SPEAKER_LIST_INDEX = "SpeakerListIndex";

    // VIP自定制投放的VIP geo列表，VIP自己选择会议投放的功能使用
    public static final String CUSTOM_VIP_GEO = "CustomVipGeo";
    // NearSen定向投放的VIP geo列表，用于ns将已有vip投放到特定会议上建立信息生态使用
    public static final String DIRECT_VIP_GEO = "DirectVipGeo";

    public static final String RANGE_HASH = "SysConstantRange:hash";
    public static final String MEETING_CENTER_RANGE = "MeetingCenterRange";
    public static final String PERSON_CENTER_RANGE = "PersonCenterRange";

    public static final String MEETING_GEO = "MeetingGeo";

    public static final String PERSON_ON_MEETING_RT = "PersonOnMeetingRealTime:hash";

    public static final String REGISTER_PHONE_CODE = "RegisterPhoneCode:hash";
    //    public static final String REGISTER_DEVICE_WAITING = "RegisterDeviceWaiting:set";
    public static final String REGISTER_DEVICE_LIMIT = "RegisterDeviceLimit:hash";
    public static final Integer REG_DEVICE_LIMIT_NUM = 6;
    public static final Long HALF_HOUR = Long.valueOf(1000 * 60 * 30);
    public static final String REGISTER_DEVICE_DAY_LIMIT = "RegisterDeviceDayLimit:hash";
    public static final Integer REG_DEVICE_DAY_LIMIT_NUM = 9;
    public static final Long A_DAY = Long.valueOf(1000 * 60 * 60 *24);
    public static final Long A_5MINS = Long.valueOf(1000 * 60 * 5);

    public static long HOURS_2 = 2 * 60 * 60 * 1000;
    public static long HOURS_1 = 1 * 60 * 60 * 1000;

    public static final int TOTAL_PERSONS = 500;
    public static final int PAGE_MAX_ITEMS = 10;
    public static final Double DIS_RANGE = 0.5; // 公里

    public static final String PRODUCT_PAYLIST = "ProductPaylist:hash";
    public static final String TOTAL = ":total";
    public static final String PAYING = ":paying";
    public static final String PAYED = ":payed";
    public static final String LEFT = ":left";

    public static final String ORDER_PAYING = "ORDER_PAYING";
    public static final String ORDER_PAID = "ORDER_PAID";
    public static final String ORDER_FAILED = "ORDER_FAILED";
    public static final String ORDER_CANCEL = "ORDER_CANCEL";

    public static final String WXORDER_SUCCESS = "SUCCESS";
    public static final String WXORDER_REFUND = "REFUND";
    public static final String WXORDER_NOTPAY = "NOTPAY";
    public static final String WXORDER_CLOSED = "CLOSED";
    public static final String WXORDER_REVOKED = "REVOKED";
    public static final String WXORDER_USERPAYING = "USERPAYING";
    public static final String WXORDER_CPAYERROR = "PAYERROR";

    public static final String VERSION_KEY = "SysConstantVersion:hash";
    public static final String IOS_MIN_VERSION = "0.6.0";
    public static final String IOS_NEWEST_VERSION = "0.6.3";
    //    public static final int IOS_MIN_VERSION_NUM = 160;
//    public static final int IOS_NEWEST_VERSION_NUM = 160;
    public static final String ANDROID_MIN_VERSION = "0.6.0";
    public static final String ANDROID_NEWEST_VERSION = "0.6.3";
    public static final int ANDROID_MIN_VERSION_NUM = 160;
    public static final int ANDROID_NEWEST_VERSION_NUM = 163;
//    public static final String XIAOCHENGXU_MIN_VERSION = "0.5";
//    public static final int XIAOCHENGXU_MIN_VERSION_NUM = 160;

    public static final String TEST_PHONE = "17090083839";
    public static final String TEST_CODE = "838399";

    public static final String PROFILE_PREFIX = "web:profile:";
    public static final String IDENTITY_PREFIX = "identity:BUSINESS_";

    public static final String DOC_TYPE_BUSINESSTALK = "businesstalk";
    public static final String DOC_TYPE_BUSINESSTALK_DEL = "businesstalk:del";


    public static final String CARD_MESSAGE = "card_message";       // 晒
    public static final String CARD_QUESTION = "card_question";     // 问
    public static final String CARD_PROJECT = "card_project";       // 创业项目
    public static final String CARD_ACTIVITY = "card_activity";     // 线下活动
    public static final String CARD_PROVIDE = "card_provide";       // 提供
    public static final String CARD_REQUIRE = "card_require";       // 寻求
    public static final String CARD_SHARE = "card_share";           // 未使用
    public static final String CARD_FAST = "card_fast";             // 快速话题
    public static final String CARD_PRODUCT = "card_product";       // 产品
    public static final String CARD_SERVICE = "card_service";       // 服务
    public static final String CARD_YILABAO = "card_yilabao";       // 易拉宝
    public static final String CARD_XUANCHUANCE = "card_xuanchuance";   // 宣传册
    public static final String CARD_EXCHANGE = "card_exchange";         // 资源交换
    public static final String CARD_MEETUP = "card_meetup";         // 约见（主讲人发出）

    public static final String ACTION_COMMENT = "ACTION_COMMENT";
    public static final String ACTION_LIKE = "ACTION_LIKE";
    public static final String ACTION_SAVE = "ACTION_SAVE";
    public static final String ACTION_SHARE = "ACTION_SHARE";
    public static final String ACTION_ASKSOMEBODY = "ACTION_ASKSOMEBODY";   // 问 问别人
    public static final String ACTION_SAMEQUESTION = "ACTION_SAMEQUESTION"; // 问 同问
    public static final String ACTION_ANSWER = "ACTION_ANSWER";             // 问 回答
    public static final String ACTION_INVEST = "ACTION_INVEST";         // 创业项目 投资
    public static final String ACTION_REPORT = "ACTION_REPORT";         // 创业项目 报道
    public static final String ACTION_COOPERATE = "ACTION_COOPERATE";   // 创业项目 合作
    public static final String ACTION_JOIN = "ACTION_JOIN";             // 创业项目 加入
    public static final String ACTION_FEESUPPORT = "ACTION_FEESUPPORT";     // 线下活动 赞助
    public static final String ACTION_ENROLL = "ACTION_ENROLL";             // 线下活动 注册
    public static final String ACTION_WANT = "ACTION_WANT";         // 提供 抢
    public static final String ACTION_PROVASK = "ACTION_PROVASK";   // 提供 问
    public static final String ACTION_GIVE = "ACTION_GIVE";         // 寻求 给
    public static final String ACTION_REQUASK = "ACTION_REQUASK";   // 寻求 问

    public static final String ACTION_PROD_GRAP = "ACTION_PROD_GRAP";   // 产品 抢
    public static final String ACTION_PROD_ASK = "ACTION_PROD_ASK";     // 产品 问
    public static final String ACTION_SRVC_GRAP = "ACTION_SRVC_GRAP";   // 服务 抢
    public static final String ACTION_SRVC_ASK = "ACTION_SRVC_ASK";     // 服务 问
    public static final String ACTION_YLB_GRAP = "ACTION_YLB_GRAP";     // 易拉宝 抢
    public static final String ACTION_YLB_ASK = "ACTION_YLB_ASK";       // 易拉宝 问
    public static final String ACTION_XCC_GRAP = "ACTION_XCC_GRAP";     // 宣传册 抢
    public static final String ACTION_XCC_ASK = "ACTION_XCC_ASK";       // 宣传册 问
    public static final String ACTION_EXCHG_EX = "ACTION_EXCHG_EX";     // 交换 换
    public static final String ACTION_EXCHG_ASK = "ACTION_EXCHG_ASK";   // 交换 问

    public static final String ACTION_VIP_PASS = "ACTION_VIP_PASS";   //


    public static final String ACTION_MEETUP_GRAP = "ACTION_MEETUP_GRAP";   // 约见 秒抢

    public static final String CARD_BUSINESS_OPEN = "CARD_BUSINESS_OPEN";       // 话题 商务动作 有效
    public static final String CARD_BUSINESS_CLOSE = "CARD_BUSINESS_CLOSE";     // 话题 商务动作 关闭
    public static final String PERSON_BUSINESS_OPEN = "PERSON_BUSINESS_OPEN";   // 个人会话 商务动作 有效
    public static final String PERSON_BUSINESS_CLOSE = "PERSON_BUSINESS_CLOSE"; // 个人会话 商务动作 关闭

    public static final String WHOBUYIT_NEARSEN = "NEARSEN";
    public static final String WHOBUYIT_VIP = "VIP";
    public static final String WHOBUYIT_COMPANY = "COMPANY";

    public static final String ORDER_PRODID_TIP = "SearchBySelf";

    public static final String PRODUCT_SPEAKERTALK = "PRODUCT_SPEAKERTALK";
    public static final String PRODUCT_MEETINGPUTON = "PRODUCT_MEETINGPUTON";
    public static final String PRODUCT_SPEAKERPPT = "PRODUCT_SPEAKERPPT";

    public static final String ORDER_SPEAKERTALK = "ORDER_SPEAKERTALK";
    public static final String ORDER_MEETINGPUTON = "ORDER_MEETINGPUTON";
    public static final String ORDER_SPEAKERPPT = "ORDER_SPEAKERPPT";

//    public static final String NEARSEN_ADMIN_DOCID = PropertiesUtils.getProperties().getProperty("nearseniddocid");

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
