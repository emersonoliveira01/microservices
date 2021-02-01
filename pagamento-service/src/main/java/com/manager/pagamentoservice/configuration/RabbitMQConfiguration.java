package com.manager.pagamentoservice.configuration;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfiguration {
    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
