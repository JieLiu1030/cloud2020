package com.atlj.springcloud.controller;

import com.atlj.springcloud.entities.CommonResult;
import com.atlj.springcloud.entities.Payment;
import com.atlj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("*****插入结果： " + result);

        if (result > 0){
            return new CommonResult(200,"插入数据库成功 + serverPort=" + serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败，",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment result = paymentService.getPaymentById(id);
        log.info("****查询结果： " + result);

        if (result != null){
            return new CommonResult(200,"查询成功 serverPort=" + serverPort,result);
        }else{
            return new CommonResult(444,"没有对应记录，id= ，" + id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/timeout")
    public String  paymentTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
