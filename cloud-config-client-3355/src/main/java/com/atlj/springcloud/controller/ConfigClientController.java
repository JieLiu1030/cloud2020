package com.atlj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /*
    在github上改变配置后，配置服务中心可直接更新，但是配置服务客户端需要发送POST请求才能避免需要重启服务器完成刷新
    http://localhost:3355/actuator/refresh
     */
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
