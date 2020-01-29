package com.adminsys.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author Qiang
 * @version 1.0
 * @description 使用任务调度实现自动化补偿
 * @date 2020/1/29 下午12:40
 **/
@Component
public class PayJobService {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        System.out.println("XXL-JOB, Hello World.");
        return ReturnT.SUCCESS;
    }
}
