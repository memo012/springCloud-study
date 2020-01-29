package com.adminsys.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/29 下午3:25
 **/
@Component
public class PayMqConfig {


    /**
     * 积分队列
     */
    private final String ROUTING_INTEGRAL_QUEUE = "routing_integral_queue";

    /**
     * 支付补偿队列
     */
    private final String ROUTING_CREATE_PAY_QUEUE = "routing_create_pay_queue";

    /**
     *  交换机
     */
    private final String ROUTING_EXCHANGE= "integral_exchange_name";

    /**
     *  定义积分队列
     * @return
     */
    @Bean
    public Queue newSendQueue(){
        return new Queue(ROUTING_INTEGRAL_QUEUE);
    }

    /**
     *  定义支付补偿队列
     * @return
     */
    @Bean
    public Queue newRepQueue(){
        return new Queue(ROUTING_CREATE_PAY_QUEUE);
    }


    /**
     *  定义交换机
     * @return
     */
    @Bean
    public DirectExchange directOrderExchange(){
        return new DirectExchange(ROUTING_EXCHANGE);
    }

    /**
     *  .订单队列与交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeOrderQueue(){
        return BindingBuilder.bind(newSendQueue()).to(directOrderExchange()).with("integralRoutingKey");
    }

    /**
     *  .订单队列与交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeCreateOrder(){
        return BindingBuilder.bind(newRepQueue()).to(directOrderExchange()).with("integralRoutingKey");
    }

}
