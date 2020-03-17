package com.atlj.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atlj.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName()+" paymentInfo_ok, 运行正常";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        int timenumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int i = 10/0;
        return "线程池：" + Thread.currentThread().getName()+" paymentInfo_timeout, 耗时:";
    }

    //全局服务降级fallback方法
    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName()+" 系统繁忙或运行时出错，请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if (id<0){
            throw new RuntimeException("*******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" +serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id:" + id+" 不能为负数，请稍后再试！";
    }

}
