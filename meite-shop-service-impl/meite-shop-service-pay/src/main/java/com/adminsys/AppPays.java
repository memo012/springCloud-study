package com.adminsys;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/14 上午11:00
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
@EnableAsync
@MapperScan("com.adminsys.service.dao")
public class AppPays {
    public static void main(String[] args) {
        SpringApplication.run(AppPays.class, args);
    }
}
