package com.example.mall.portal.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class miaoshaRabbitMqConfig {


    
    //定义imQueue的队列
    @Bean
    public Queue miaoshaQueue() {
        return new Queue("miaosha.queue");
    }

    //定义名字为x的交换机
    @Bean
    DirectExchange miaoshaExchange() {
        return new DirectExchange("miaosha.x");
    }


    //绑定队列和交换机 routingKey为topic.#
    @Bean
    Binding bindmiaosha() {
        return BindingBuilder.bind(miaoshaQueue()).to(miaoshaExchange()).with("miaoshaKey");
    }

}
