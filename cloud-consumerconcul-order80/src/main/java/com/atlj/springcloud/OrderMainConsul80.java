package com.atlj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMainConsul80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainConsul80.class);
    }
}
