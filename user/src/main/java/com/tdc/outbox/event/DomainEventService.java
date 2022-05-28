package com.tdc.outbox.event;

import com.tdc.outbox.event.infraestructure.DomainEventRepository;
import com.tdc.outbox.event.publishing.PublishingStatus;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainEventService {

    private final DomainEventRepository domainEventRepository;

    public DomainEventService(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    public DomainEvent save(final DomainEvent domainEvent) {
        return this.domainEventRepository.save(domainEvent);
    }

    public List<DomainEvent> getPendingEvents() {
        return this.domainEventRepository.findByPublishingStatus(PublishingStatus.PENDING);
    }
}