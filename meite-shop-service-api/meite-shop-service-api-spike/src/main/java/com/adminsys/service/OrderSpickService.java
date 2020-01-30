package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description 查询秒杀记录
 * @date 2020/1/30 下午12:33
 **/
public interface OrderSpickService {
    /**
     *  查询订单记录
     * @param phone
     * @param seckillId
     * @return
     */
    @RequestMapping("/getOrder")
    public BaseResponse<JSONObject> getOrder(String phone, Long seckillId);

}
