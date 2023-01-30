package com.ticketreservation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class SMSQueue {
    private String queueName = "ticket-reservation.notification.sms";

    private String exchange = "ticket-reservation.notification.sms";

    @Bean
    public Queue smsQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue smsQueue, DirectExchange smsExchange) {
        return BindingBuilder.bind(smsQueue).to(smsExchange).with("");
    }
}
