package com.tdc.outbox.event.publishing.infraestructure.broker.vendor.rabbitmq;

import com.tdc.outbox.event.DomainEvent;
import com.tdc.outbox.event.publishing.infraestructure.broker.AMQPMessageBroker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqMessageBroker implements AMQPMessageBroker {

    private final RabbitTemplate rabbitTemplate;

    public RabbitmqMessageBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public DomainEvent publish(final String routingKey, final DomainEvent domainEvent) {
        this.rabbitTemplate.convertAndSend(routingKey, domainEvent);
        
        return domainEvent;
    }
}