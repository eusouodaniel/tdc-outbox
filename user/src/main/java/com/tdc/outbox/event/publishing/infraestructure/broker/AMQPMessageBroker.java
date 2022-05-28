package com.tdc.outbox.event.publishing.infraestructure.broker;

import com.tdc.outbox.event.DomainEvent;

public interface AMQPMessageBroker {
    DomainEvent publish(final String routingKey, final DomainEvent domainEvent);
}