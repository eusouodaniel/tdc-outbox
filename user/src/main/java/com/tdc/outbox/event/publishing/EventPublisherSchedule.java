package com.tdc.outbox.event.publishing;

import com.tdc.outbox.event.DomainEvent;
import com.tdc.outbox.event.DomainEventService;
import com.tdc.outbox.event.publishing.infraestructure.broker.AMQPMessageBroker;
import com.tdc.outbox.event.publishing.infraestructure.broker.RoutingKeyResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherSchedule {

    private final Logger log = LoggerFactory.getLogger(EventPublisherSchedule.class);

    private final DomainEventService domainEventService;
    private final AMQPMessageBroker eventBus;
    private final RoutingKeyResolver routingKeyResolver;

    public EventPublisherSchedule(DomainEventService domainEventService, AMQPMessageBroker eventBus, RoutingKeyResolver routingKeyResolver) {
        this.domainEventService = domainEventService;
        this.eventBus = eventBus;
        this.routingKeyResolver = routingKeyResolver;
    }

    @Scheduled(fixedDelayString = "${event.publisher.rate}")
    public void publishEvents() {
        log.info("Processing pending messages...");
        this.domainEventService
                .getPendingEvents()
                .stream()
                .map(this::publish)
                .forEach(this.domainEventService::save);
    }

    private DomainEvent publish(final DomainEvent eventToPublish) {
        log.info("Publishing message: '{}'", eventToPublish);
        this.eventBus.publish(this.routingKeyResolver.resolve(eventToPublish.getEventType()), eventToPublish);
        eventToPublish.markAsPublished();
        
        return eventToPublish;
    }
}