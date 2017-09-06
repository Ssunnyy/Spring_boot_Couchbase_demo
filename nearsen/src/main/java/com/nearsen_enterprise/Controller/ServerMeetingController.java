package com.nearsen_enterprise.Controller;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.Sort;
import com.nearsen_enterprise.Util.MD5;
import com.nearsen_enterprise.Config.ConstantsMeetingInterface;
import com.nearsen_enterprise.Util.DateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.s;
import static com.couchbase.client.java.query.dsl.Expression.x;


@RestController
@RequestMapping("/api")
public class ServerMeetingController {

    private long startts;
    private long endts;
    private long personsnum;
    private int currecordindex;

    @Autowired
    Bucket meetingBucket;
    @Autowired
    private Bucket couchbaseBucket;

    private N1qlQueryResult nresult;


//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
    @ResponseBody
    @RequestMapping(value = ConstantsMeetingInterface.meeting_select_endpoint, method = RequestMethod.POST)
    @ApiOperation(nickname = "createSelected",value = "创建会议",notes = "使用POST方式")
    public ResponseEntity<JSONObject> createSelected(
            @RequestBody String jsonstr) throws Exception {

        String retcode = "1";
        String returnmsg = "会议创建成功";
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonstr);
        Long time = System.currentTimeMillis();
        String distimestr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(time));

        String docid = "meeting:" + MD5.md5(obj.getString("title").trim());
        if (!obj.getBoolean("isselected")) {
            JsonDocument jd = meetingBucket.get(docid);
            if (null != jd) {
                JsonObject jo = jd.content();
                jo.put("isselected", obj.getBoolean("isselected"));
                meetingBucket.upsert(JsonDocument.create(docid, jo));
            }
        } else {
            // create meeting
            JsonObject meeting = JsonObject.empty()
                    .put("type", obj.getString("type"))
                    .put("title", obj.getString("title"))
                    .put("time", obj.getString("time"))
                    .put("startts", String.valueOf(obj.getLong("startts")))
                    .put("endts", String.valueOf(obj.getLong("endts")))
                    .put("location", obj.getString("location"))
                    .put("lati", obj.getString("lati"))
                    .put("longi", obj.getString("longi"))
                    .put("persons", obj.getString("persons"))
                    .put("personsnum", obj.getString("personsnum"))
                    .put("isselected", obj.getBoolean("isselected"))
                    .put("createtimelong", time.toString())
                    .put("createtimestr", distimestr)
                    .put("detailpageurl", obj.getString("detailpageurl"))
                    .put("tagliststr", obj.getString("tagliststr"))

                    ;
            meetingBucket.upsert(JsonDocument.create(docid, meeting));
        }

        result.put("code", retcode);
        result.put("message", returnmsg);
        result.put("timestamp", time.toString());
        result.put("data", data.toString());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "v2/meeting/get", method = RequestMethod.POST)
    @ApiOperation(nickname = "getMeeting",value = "获取会议列表",notes = "使用POST方式")
    public ResponseEntity<JSONObject>getMeeting(
            @ApiParam(name = "weak",value = "0本周、1下会议、2下下周",defaultValue = "0") @RequestParam int weak,
            @ApiParam(name = "meetingName",value = "会议名称",defaultValue = "",required = false) @RequestParam String meetingName,
            @ApiParam(name = "meetManCount",value = "会议人数",defaultValue = "0") @RequestParam long meetManCount){

        String retcode = "1";
        String returnmsg = "投放会议获取成功";
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray meetingArray = new JSONArray();
        Long time = System.currentTimeMillis();

        switch (weak){
            case 0:{
                startts = DateUtils.getCurWeekFstDayStartTs();
                endts = DateUtils.getCurWeekLastDayLastTs();
            }
            case 1:{
                startts = DateUtils.getCurWeekLastDayLastTs() + 1;
                endts = DateUtils.getNextNWeekLastDayLastTs(1);
            }
            case 2:{
                startts = DateUtils.getNextNWeekLastDayLastTs(1) + 1;
                endts = DateUtils.getNextNWeekLastDayLastTs(2);
            }
        }

        if (meetManCount > 0) {
            personsnum = meetManCount;
        }

        Statement statement = select("title", "tsstart", "tsend", "location",
                "time", "persons", "owner", "isselected", "meta(t).id", "detailpageurl",
                "memo", "tagliststr","longi","lati")
                .from(Expression.i(ConstantsMeetingInterface.meetingBucket) + " t")
                .where(x("type").eq(s("meeting"))
                        .and(x("tsstart").gt(startts))
                        .and(x("tsstart").lt(endts)))
                .orderBy(Sort.asc("tsstart"));

        nresult = meetingBucket.query(statement);


        for (N1qlQueryRow row : nresult) {
            JsonObject curcardobj = row.value();
            meetingArray.add(curcardobj.toString());
        }

        data.put("thisweekmeeting", meetingArray);

        result.put("code", retcode);
        result.put("message", returnmsg);
        result.put("timestamp", time.toString());
        result.put("data", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = ConstantsMeetingInterface.meeting_tovip_endpoint, method = RequestMethod.POST)
    @ApiOperation(nickname = "pubToVip",value = "会议投放/取消",notes = "使用POST方式")
    public ResponseEntity<JSONObject> pubToVip(
            @RequestBody String jsonstr) throws Exception {

        String retcode = "1";
        String returnmsg = "会议投放VIP成功或者取消成功";
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonstr);
        Long time = System.currentTimeMillis();
        String distimestr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(time));

        String docid = "meeting:" + MD5.md5(obj.getString("title").trim());
        if (!obj.getBoolean("pubtovip")) {
            String prodDocId = "product:" + docid;
            couchbaseBucket.remove(prodDocId);
            couchbaseBucket.remove(docid);
        } else {
            JsonDocument jd = couchbaseBucket.get(docid);
            if (null != jd) {
                JsonObject jo = jd.content();
                jo.put("pubtovip", obj.getBoolean("pubtovip"));
                jo.put("putonprice", obj.getInt("putonprice"));
                jo.put("personsnum", obj.getInt("personsnum"));
                couchbaseBucket.upsert(JsonDocument.create(docid, jo));

                String prodDocId = "product:" + docid;
                generateMeetingProductDoc(couchbaseBucket, prodDocId, jo);
            }
        }

        result.put("code", retcode);
        result.put("message", returnmsg);
        result.put("timestamp", time.toString());
        result.put("data", data.toString());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = ConstantsMeetingInterface.meeting_checkloction_endpoint, method = RequestMethod.POST)
    @ApiOperation(nickname = "checkloction",value = "更新会议举办地址",notes = "使用POST方式")
    public ResponseEntity<JSONObject> checkloction(
            @ApiParam(name = "meetingId",value = "会议Id",required = true) @RequestParam String meetingId,
            @ApiParam(name = "latitude",value = "纬度",required = true) @RequestParam String latitude,
            @ApiParam(name = "longitude",value = "经度",required = true) @RequestParam String longitude) throws Exception {

        String retcode = "1";
        String returnmsg = "会议位置更新成功";
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Long time = System.currentTimeMillis();

        JsonDocument jd = meetingBucket.get(meetingId);
        if (null != jd) {
            JsonObject jo = jd.content();
            if (null != latitude && null != longitude){
                jo.put("lati", latitude);
                jo.put("longi", longitude);
                meetingBucket.upsert(JsonDocument.create(meetingId, jo));
            }
        }

        result.put("code", retcode);
        result.put("message", returnmsg);
        result.put("timestamp", time.toString());
        result.put("data", data.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    private String generateMeetingProductDoc(Bucket globalBucket, String prodDocId, JsonObject jo) {

        jo.put("type", "product");
        jo.put("productid", prodDocId);
        globalBucket.upsert(JsonDocument.create(prodDocId, jo));

        return  prodDocId;
    }

    @ResponseBody
    @RequestMapping(value = ConstantsMeetingInterface.meeting_version_endpoint, method = RequestMethod.POST)
    @ApiOperation(nickname = "selectmeetingversion",value = "软件版本信息",notes = "使用POST方式")
    public ResponseEntity<JSONObject> selectmeetingversion() throws Exception {

        JSONObject sysinfo = new JSONObject();
//        String meetingver = (String) stringRedisTemplate.opsForHash().get(VERSION_KEY, "selectmeeting");
        String meetingver = "2";
        sysinfo.put("version", Integer.valueOf(meetingver));

        JSONObject result = new JSONObject();
        result.put("code", "1");
        result.put("message", "系统信息获取成功");
        result.put("timestamp", String.valueOf(System.currentTimeMillis()));
        result.put("data", sysinfo.toString());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
