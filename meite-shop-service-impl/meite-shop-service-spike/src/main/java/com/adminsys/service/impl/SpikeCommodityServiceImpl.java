package com.adminsys.service.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.mq.producer.SpikeCommodityProducer;
import com.adminsys.service.SpikeCommodityService;
import com.adminsys.service.mapper.SeckillMapper;
import com.adminsys.service.mapper.entity.SeckillEntity;
import com.adminsys.token.GenerateToken;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/30 下午12:50
 **/
@RestController
@Slf4j
public class SpikeCommodityServiceImpl extends BaseApiService<JSONObject> implements SpikeCommodityService {

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SpikeCommodityProducer spikeCommodityProducer;

    @Autowired
    private GenerateToken generateToken;

    @Override
    @Transactional
    public BaseResponse<JSONObject> spike(String phone, Long seckillId) {
        log.info("###>>>>>秒杀接口线程池名称:" + Thread.currentThread().getName());
        // 1.参数验证
        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码不能为空!");
        }
        if (seckillId == null) {
            return setResultError("商品库存id不能为空!");
        }
        // 2.从redis从获取对应的秒杀token
        String seckillToken = generateToken.getListKeyToken(seckillId + "");
        if (StringUtils.isEmpty(seckillToken)) {
            log.info(">>>seckillId:{}, 亲，该秒杀已经售空，请下次再来!", seckillId);
            return setResultError("亲，该秒杀已经售空，请下次再来!");
        }
        // 3.获取到秒杀token之后，异步放入mq中实现修改商品的库存
        sendSeckillMsg(seckillId, phone);
        return setResultSuccess("抢购成功");
    }

    /**
     * 获取到秒杀token之后，异步放入mq中实现修改商品的库存
     */
    @Async
    public void sendSeckillMsg(Long seckillId, String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seckillId", seckillId);
        jsonObject.put("phone", phone);
        spikeCommodityProducer.send(jsonObject);
    }

    @Override
    public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity) {
        // 1.验证参数
        if (seckillId == null) {
            return setResultError("商品库存id不能为空!");
        }
        if (tokenQuantity == null) {
            return setResultError("token数量不能为空!");
        }
        SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
        if (seckillEntity == null) {
            return setResultError("商品信息不存在!");
        }
        // 2.使用多线程异步生产令牌
        createSeckillToken(seckillId, tokenQuantity);
        return setResultSuccess("令牌正在生成中.....");
    }
    @Async
    public void createSeckillToken(Long seckillId, Long tokenQuantity) {
        generateToken.createListToken("seckill_", seckillId + "", tokenQuantity);
    }
}
