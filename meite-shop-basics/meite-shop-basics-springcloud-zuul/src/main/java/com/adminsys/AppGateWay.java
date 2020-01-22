package com.adminsys;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiang
 * @version 1.0
 * @description 微服务网关入口
 * @date 2019/12/4 下午8:16
 **/
@SpringBootApplication
@EnableSwagger2Doc
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@MapperScan("com.adminsys.mapper")
public class AppGateWay {
    public static void main(String[] args) {
        SpringApplication.run(AppGateWay.class, args);
    }


    /**
     *  添加文档来源
     */
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider{
        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            // 网关使用服务别名获取远程服务的SwaggerApi
            resources.add(swaggerResource("app-member", "/app-member/v2/api-docs", "2.0"));
            resources.add(swaggerResource("app-weixin", "/app-weixin/v2/api-docs", "2.0"));
            return resources;
        }
    }

    private SwaggerResource swaggerResource(String name, String location, String version){
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setLocation(location);
        swaggerResource.setName(name);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
