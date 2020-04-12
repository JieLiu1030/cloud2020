package com.atlj.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("testA")
    public String testA() throws InterruptedException {
        //TimeUnit.MILLISECONDS.sleep(800);
        log.info(Thread.currentThread().getName() + "\t" + "-----testB");
        return "testA---";
    }

    @GetMapping("testB")
    public String testB(){
        return "testB---";
    }
}
