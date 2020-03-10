package com.atlj.springcloud.controller;

import com.atlj.springcloud.entities.CommonResult;
import com.atlj.springcloud.entities.Payment;
import com.atlj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("*****插入结果： " + result);

        if (result > 0){
            return new CommonResult(200,"插入数据库成功",result);
        }else{
            return new CommonResult(444,"插入数据库失败，",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment result = paymentService.getPaymentById(id);
        log.info("****查询结果： " + result);

        if (result != null){
            return new CommonResult(200,"查询成功",result);
        }else{
            return new CommonResult(444,"没有对应记录，id= ，" + id,null);
        }
    }
}
