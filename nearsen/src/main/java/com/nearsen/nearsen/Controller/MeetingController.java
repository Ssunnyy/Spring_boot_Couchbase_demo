package com.nearsen.nearsen.Controller;


import com.nearsen.nearsen.Model.Meeting;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

import static com.couchbase.client.java.query.Select.select;


@RestController
@RequestMapping("/api/admin")
public class MeetingController {



    private static final String template = "hello,%s";
    private AtomicLong counter = new AtomicLong();
//    @Value("${storage.loginBucket}")
//    String bucketname;
//    @Autowired
//    private Bucket globalBucket

//    ConnectionManager connectManager = new ConnectionManager();
//    private String bucketname = connectManager.getLoginBucket();
//    private Bucket globalBucket = connectManager.loginBucket();

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation(nickname = "login",value = "用户登陆",notes = "Get 方式用户登陆")
    public Meeting login (HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "用户名") @RequestParam String name,
                            @ApiParam(value = "密码") @RequestParam String password) {
        return new Meeting(counter.incrementAndGet(),
                String.format(template, name));
    }

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

}
