package com.atlj.springcloud.service;

public interface PaymentService {

    public String paymentInfo_ok(Integer id);

    public String paymentInfo_timeout(Integer id);
}
