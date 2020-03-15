package com.atlj.springcloud.controller;

import com.atlj.springcloud.entities.CommonResult;
import com.atlj.springcloud.entities.Payment;
import com.atlj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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

    /**
     * 获取Euraka中的注册服务信息，以及根据服务名称获取对应每个实例的信息
     * @return
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******service:" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() +"\t" +instance.getPort() + "\t" + instance.getHost() +"\t"+instance.getUri());
        }
        return this.discoveryClient;
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
