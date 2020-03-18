package com.atlj.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route_name的路由规则
     * 当网址访问http://localhost:9527/guoji的时候就会自动转发到地址http://news.baidu.com/guoji
     * @param locatorBuilder
     * @return
     */
    @Bean
    public RouteLocator consumerRouteLocator(RouteLocatorBuilder locatorBuilder){

        RouteLocatorBuilder.Builder routes =  locatorBuilder.routes();
        routes.route("path_route_atlj",r ->
                r.path("/guoji").uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
