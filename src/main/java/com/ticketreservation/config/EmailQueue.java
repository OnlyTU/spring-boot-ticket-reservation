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
public class EmailQueue {
    private String queueName = "ticket-reservation.notification.email";

    private String exchange = "ticket-reservation.notification.email";

    @Bean
    public Queue emailQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with("");
    }
}
