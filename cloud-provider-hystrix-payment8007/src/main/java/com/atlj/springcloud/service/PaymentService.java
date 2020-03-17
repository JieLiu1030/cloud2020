package com.atlj.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public String paymentInfo_ok(Integer id);

    public String paymentInfo_timeout(Integer id);

    public String paymentCircuitBreaker(Integer id);
}
