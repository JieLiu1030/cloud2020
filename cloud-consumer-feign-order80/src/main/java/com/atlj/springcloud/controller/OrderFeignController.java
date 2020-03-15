package com.atlj.springcloud.controller;

import com.atlj.springcloud.entities.CommonResult;
import com.atlj.springcloud.entities.Payment;
import com.atlj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public String  paymentTimeOut(){
        return paymentService.paymentTimeOut();
    }
}
