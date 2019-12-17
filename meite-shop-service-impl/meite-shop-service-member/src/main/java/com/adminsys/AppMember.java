package com.adminsys;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Qiang
 * @version 1.0
 * @description 会员服务实现
 * @date 2019/12/4 下午6:40
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class, args);
    }
}
