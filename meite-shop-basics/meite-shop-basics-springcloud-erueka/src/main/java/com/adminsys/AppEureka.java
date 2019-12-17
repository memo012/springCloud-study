package com.adminsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Qiang
 * @version 1.0
 * @description Eureka
 * @date 2019/12/4 下午12:57
 **/
@SpringBootApplication
@EnableEurekaServer
public class AppEureka {

    public static void main(String[] args) {
        SpringApplication.run(AppEureka.class, args);
    }

}
