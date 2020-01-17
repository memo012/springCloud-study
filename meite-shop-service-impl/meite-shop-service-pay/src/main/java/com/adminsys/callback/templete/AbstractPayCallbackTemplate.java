package com.adminsys.callback.templete;

import com.adminsys.constant.PayConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Qiang
 * @version 1.0
 * @description 使用模版方法重构异步回调代码
 * @date 2020/1/17 下午7:46
 **/
@Slf4j
@Component
public abstract class AbstractPayCallbackTemplate {

    /**
     * 获取所有请求的参数，封装成Map集合 并且验证是否被篡改
     *
     * @param req
     * @param resp
     * @return
     */
    protected abstract Map<String, String[]> verifySignature(HttpServletRequest req, HttpServletResponse resp);

    /**
     * 异步回调执行业务逻辑
     * @param verifySignature
     */
    @Transactional
    public abstract String asyncService(Map<String, String[]> verifySignature);

    /**
     *  失败处理
     * @return
     */
    public abstract String failResult();

    /**
     *  成功处理
     * @return
     */
    public abstract String successResult();

    /**
     * *1. 将报文数据存放到es <br>
     * 1. 验证报文参数<br>
     * 2. 将日志根据支付id存放到数据库中<br>
     * 3. 执行的异步回调业务逻辑<br>
     *
     */
    @Transactional
    public String asyncCallBack(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 验证报文参数 相同点 获取所有的请求参数封装成为map集合 并且进行参数验证
        Map<String, String[]> verifySignature = verifySignature(req, resp);
        // 2.将日志根据支付id存放到数据库中
        String[] paymentId = verifySignature.get("paymentId");
        if (StringUtils.isEmpty(paymentId[0])) {
            return failResult();
        }
        log.info(">>>>>asyncCallBack service 01");
        // 3.采用异步形式写入日志到数据库中
        log.info(">>>>>asyncCallBack service 04");
        String[] result = verifySignature.get(PayConstant.RESULT_NAME);
        // 4.201报文验证签名失败
        if (result[0].equals(PayConstant.RESULT_PAYCODE_201)) {
            return failResult();
        }
        // 5.执行的异步回调业务逻辑
        return asyncService(verifySignature);
    }



}
