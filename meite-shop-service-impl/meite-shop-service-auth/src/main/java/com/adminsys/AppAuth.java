package com.adminsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/22 下午5:42
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.adminsys.mapper")
public class AppAuth {
    public static void main(String[] args) {
        SpringApplication.run(AppAuth.class);
    }
}
