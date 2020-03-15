package com.kelab.api;

import cn.wzy.verifyUtils.annotation.EnableVerify;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableVerify
@MapperScan(basePackages = "com.kelab.api.dal.dao")
@EnableFeignClients(basePackages = "com.kelab.api.service")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
