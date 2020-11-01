package com.youfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YoufanRegisterStart {
    public static void main(String[] args) {
        SpringApplication.run(YoufanRegisterStart.class,args);
    }
}
