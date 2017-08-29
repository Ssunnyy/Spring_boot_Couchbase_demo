package com.nearsen.nearsen.Controller;


import com.nearsen.nearsen.Model.Meeting;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/admin")
public class MeetingController {

    private static final String template = "hello,%s";
    private AtomicLong counter = new AtomicLong();

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation(nickname = "login",value = "用户登陆",notes = "Get 方式用户登陆")
    public Meeting login (HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "用户名") @RequestParam String name,
                            @ApiParam(value = "密码") @RequestParam String password) {
        return new Meeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
