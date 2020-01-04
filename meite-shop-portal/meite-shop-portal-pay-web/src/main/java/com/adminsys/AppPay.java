package com.adminsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/4 下午5:20
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
public class AppPay {
    public static void main(String[] args) {
        SpringApplication.run(AppPay.class, args);
    }
}
