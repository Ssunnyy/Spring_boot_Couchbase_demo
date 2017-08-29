package com.nearsen.nearsen.Controller;


import com.nearsen.nearsen.Model.Meeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MeetingController {

    private static final String template = "hello,%s";
    private AtomicLong counter = new AtomicLong();

    @RequestMapping("/meeting")
    public Meeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Meeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
