//package com.nearsen_enterprise.Controller;
//
//
//import com.couchbase.client.java.Bucket;
//import com.couchbase.client.java.document.JsonDocument;
//import com.couchbase.client.java.document.json.JsonArray;
//import com.couchbase.client.java.document.json.JsonObject;
//import com.couchbase.client.java.query.N1qlQueryResult;
//import com.couchbase.client.java.query.N1qlQueryRow;
//import com.couchbase.client.java.query.Statement;
//import com.couchbase.client.java.query.dsl.Sort;
//import com.couchbase.client.java.view.Stale;
//import com.couchbase.client.java.view.ViewQuery;
//import com.couchbase.client.java.view.ViewResult;
//import com.nearsen_enterprise.Model.UserModel;
//import com.nearsen_enterprise.Repository.UserRepository;
//import com.nearsen_enterprise.Util.DateUtils;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONSerializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.atomic.AtomicLong;
//
//import static com.couchbase.client.java.query.Select.select;
//import static com.couchbase.client.java.query.dsl.Expression.*;
//import static com.nearsen_enterprise.Config.Constants.HOURS_2;
//
//
//@RestController
//@RequestMapping("/api/admin")
//public class MeetingController {
//
//
//
//    private static final String templateString = "hello,%s";
//    private AtomicLong counter = new AtomicLong();
//
//    @Value("${storage.loginBucket}")
//    String bucketname;
//
//    @Autowired
//    private Bucket globalBucket;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
////
////
////    @Autowired
////    private RepositoryOperationsMapping operationsMapping;
////
////    private CouchbaseOperations couchbaseOperations = operationsMapping.getDefault();
////
////    private String bucketname = couchbaseOperations.getCouchbaseBucket().name();
////
////    private Bucket globalBucket = couchbaseOperations.getCouchbaseBucket();
//
//
//    @ResponseBody
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    @ApiOperation(nickname = "login",value = "用户登陆",notes = "Get 方式用户登陆")
//    public UserModel login (HttpServletRequest request, HttpServletResponse response,
//                            @ApiParam(value = "用户名") @RequestParam String name,
//                            @ApiParam(value = "密码") @RequestParam String password) {
//
//        UserModel userModel = userRepository.getUserModelByUserNameAndUserPassword(name,password);
//        if (userModel == null){
//            userModel = new  UserModel(String.valueOf(counter.incrementAndGet()),name,password);
//            userRepository.save(userModel);
//        }
//        return userModel;
//    }
//
//
//    @RequestMapping(value = "v2/meeting/getseleted", method = RequestMethod.POST)
//    public ResponseEntity<JSONObject> getSelected(
//            @RequestBody String jsonstr) throws Exception {
//
//        String retcode = "1";
//        String returnmsg = "会议获取成功";
//        JSONObject result = new JSONObject();
//        JSONArray data = new JSONArray();
//
//        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonstr);
//        Long time = System.currentTimeMillis();
//        String distimestr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(time));
//
//        Statement statement = select("type", "title", "location", "time",
//                "persons", "startts", "endts", "isdelete", "meta(t).id", "lati", "longi"
//        )
//                .from(i(bucketname) + " t")
//                .where(x("type").eq(s("meeting"))
//                        .and(x("isdelete").isMissing())
//                        .and(x("startts").gt(s(String.valueOf(time))))
//                )
//                .orderBy(Sort.desc("startts"))
//                ;
//        N1qlQueryResult nresult = globalBucket.query(statement);
//
//        for (N1qlQueryRow row : nresult) {
//            JsonObject curobj = row.value();
//            data.add(curobj.toString());
//        }
//
//        result.put("code", retcode);
//        result.put("message", returnmsg);
//        result.put("timestamp", time.toString());
//        result.put("data", data);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "v2/meeting/getthisweekpub", method = RequestMethod.POST)
//    public ResponseEntity<JSONObject> getThisWeekPublished(
//            @RequestBody String jsonstr) throws Exception {
//
//        String retcode = "1";
//        String returnmsg = "投放给VIP的本周会议获取成功";
//        JSONObject result = new JSONObject();
//        JSONObject data = new JSONObject();
//        JSONArray jadata = new JSONArray();
//
//        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonstr);
//        String useridmd5 = obj.getString("useridmd5");
////        String pageindex = obj.getString("pageindex");
//
//        Long time = System.currentTimeMillis();
//        String distimestr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(time));
//
//        long startts = DateUtils.getPreWeekLastDayLastTs() + 1;
//        String starttsstr = String.valueOf(startts);
//        long endts = DateUtils.getCurWeekLastDayLastTs();
//
//        // status 0-申请中，1-通过，-1-拒绝，2-停止VIP状态
//        // vipstatus -2-未申请，0-申请中，1-通过，-1-拒绝，2-停止VIP状态
//        Boolean isvip;
//        String vipdocid = "vip:" + useridmd5;
//        JsonDocument vipjd = globalBucket.get(vipdocid);
//        if (null != vipjd) {
//            String vipstatus = vipjd.content().getString("status");
//            if (vipstatus.equals("1")) {
//                isvip = true;
//            } else {
//                isvip = false;
//            }
//            data.put("isvip", isvip);
//            data.put("vipstatus", vipstatus);
//        } else {
//            isvip = false;
//            data.put("isvip", isvip);
//            data.put("vipstatus", "-2");
//        }
//
//        // 下周赠送会议个数配额
//        if (isvip) {
//            String thisyear = new SimpleDateFormat("yyyy").format(new Date());
//            int thisweekofyear = DateUtils.getWeekOfYear(new Date());
//            String presentdocid = "present:meetingputon:" + thisyear + ":" + thisweekofyear + ":" + useridmd5;
//            JsonDocument jd = globalBucket.get(presentdocid);
//            JsonObject jo = jd.content();
//            data.put("isvip", true);
//            JSONObject thisweekpresents = new JSONObject();
//            thisweekpresents.put("thisweekofyear", jo.getInt("thisweekofyear"));
//            thisweekpresents.put("meetingnum", jo.getInt("meetingnum"));
//            thisweekpresents.put("meetingnumleft", jo.getInt("meetingnumleft"));
//            data.put("thisweekpresents", thisweekpresents);
//        }
//
////        // 下周已经确认投放会议的服务所包含的会议类产品
////        Set<String> isputonmeetingprod = new HashSet<>();
////
////        JsonArray startkey = JsonArray.create();
////        startkey.add(useridmd5);
////        startkey.add(String.valueOf(startts));
////        JsonArray endkey = JsonArray.create();
////        endkey.add(useridmd5);
////        endkey.add(String.valueOf(endts));
////
////        ViewResult vresult = globalBucket.query(ViewQuery.from("vservice", "vservice")
////                .stale(Stale.FALSE).startKey(startkey).endKey(endkey));
////        for (ViewRow row : vresult) {
////            String prodid = (String) row.value();
////            isputonmeetingprod.add(prodid);
////        }
//
//        // 下周的投放会议类产品
//        Statement statement = select("type", "title", "location", "time", "persons",
//                "startts", "endts", "isdelete", "meta(t).id AS productid", "lati", "longi",
//                "putonprice", "personsnum", "detailpageurl", "tagliststr"
//        )
//                .from(i(bucketname) + " t")
//                .where(x("type").eq(s("product"))
//                                .and(x("pubtovip").eq(true))
//                                .and(x("isdelete").isMissing())
//                                .and(x("startts").gt(s(starttsstr)))
////                        .and(x("startts").lt(s(String.valueOf(endts))))
//                )
//                .orderBy(Sort.asc("startts"))
//                ;
//        N1qlQueryResult nresult = globalBucket.query(statement);
//
//        Long curts = Long.valueOf(System.currentTimeMillis());
//        Long endtslong = Long.valueOf(endts);
//
//        for (N1qlQueryRow row : nresult) {
//            JsonObject curobj = row.value();
//            String prodid = curobj.getString("productid");
//
//            if (isPuton(useridmd5, prodid)) {
//                curobj.put("isputon", true);
//            } else {
//                curobj.put("isputon", false);
//            }
//
//            String showstatus = "1"; // 1-可选；2-过期；3-未开放
//            Long meetingstartts = Long.valueOf(curobj.getString("startts"));
//            if (meetingstartts.compareTo(curts) < 0) {
//                showstatus = "2"; // 1-可选；2-过期；3-未开放
//            }
//            if (meetingstartts.compareTo(endtslong) > 0) {
//                showstatus = "3"; // 1-可选；2-过期；3-未开放
//            }
//            curobj.put("showstatus", showstatus);
//
//            jadata.add(curobj.toString());
//        }
//        data.put("thisweekmeeting", jadata);
//
//        result.put("code", retcode);
//        result.put("message", returnmsg);
//        result.put("timestamp", time.toString());
//        result.put("data", data);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    private Boolean isPuton(String uidmd5, String prodid) {
//        JsonArray onekey = JsonArray.create();
//        onekey.add(uidmd5);
//        onekey.add(prodid);
//        ViewResult vresult = globalBucket.query(ViewQuery
//                .from("vservice", "vservice_uidmd5").stale(Stale.FALSE).key(onekey));
//
//        int resultnum = vresult.allRows().size();
//        if (resultnum > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @RequestMapping(value = "v2/meeting/ongoingmeeting", method = RequestMethod.POST)
//    public ResponseEntity<JSONObject> getOngogingMeeting(@RequestBody String jsonstr) throws Exception {
//
//        String retcode = "1";
//        String returnmsg = "还未结束或者开始的会议";
//        JSONObject result = new JSONObject();
//        JSONObject data = new JSONObject();
//        JSONArray jadata = new JSONArray();
//
//        Long time = System.currentTimeMillis();
//        long starttsendpos = time + HOURS_2;
//        // 下周的投放会议类产品
//        Statement statement = select("type", "startts", "endts", "isdelete", "pubtovip",
//                "meta(t).id AS meetingid", "title"
//        )
//                .from(i(bucketname) + " t")
//                .where(x("type").eq(s("meeting"))
//                        .and(x("pubtovip").eq(true))
//                        .and(x("isdelete").isMissing())
//                        .and(x("endts").gt(s(String.valueOf(time))))
//                )
//                .orderBy(Sort.asc("startts"))
//                .limit(10)
//                ;
//        N1qlQueryResult nresult = globalBucket.query(statement);
//        for (N1qlQueryRow row : nresult) {
//            JsonObject curobj = row.value();
//            jadata.add(curobj.toString());
//        }
//
//        result.put("code", retcode);
//        result.put("message", returnmsg);
//        result.put("timestamp", time.toString());
//        result.put("data", jadata);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//}
