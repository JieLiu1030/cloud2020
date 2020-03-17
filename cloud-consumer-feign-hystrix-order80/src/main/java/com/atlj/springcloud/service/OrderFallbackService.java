package com.atlj.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "OrderFallbackService ---- fall back -- paymentInfo_ok";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "OrderFallbackService ---- fall back -- paymentInfo_timeout";
    }
}
