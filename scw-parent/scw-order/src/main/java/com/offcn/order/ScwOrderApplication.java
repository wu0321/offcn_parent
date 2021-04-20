package com.offcn.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 09:29
 * @Description:
 */
//@SpringCloudApplication   //@SpringBootApplication+@EnableDiscoveryClient+@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@MapperScan("com.offcn.order.mapper")
@EnableFeignClients
public class ScwOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwOrderApplication.class);
    }
}
