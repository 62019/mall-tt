package com.example.mall.portal.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    //定义死信交换机
    @Bean
    DirectExchange dlx() {
        return new DirectExchange("DL_EXCHANGE");
    }
    //定义死信队列
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable("dlxQueue")
//                .withArgument("x-dead-letter-exchange", "DL_EXCHANGE")//到期后转发的交换机
                .withArgument("x-dead-letter-exchange", "x")//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", "imQueue")//到期后转发的路由键
                .build();
    }

    @Bean
    Binding bindingDlx() {
        return BindingBuilder.bind(orderTtlQueue()).to(dlx()).with("dlxKey");
    }






    //定义imQueue的队列
    @Bean
    public Queue firstQueue() {
        return new Queue("imQueue");
    }

    //定义名字为x的交换机
    @Bean
    DirectExchange exchange() {
        return new DirectExchange("x");
    }


    //绑定队列和交换机 routingKey为topic.#
    @Bean
    Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with("imQueue");
    }

}
