package com.tdc.outbox.event.infraestructure;

import com.tdc.outbox.event.DomainEvent;
import com.tdc.outbox.event.publishing.PublishingStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainEventRepository extends JpaRepository<DomainEvent, String> {
    List<DomainEvent> findByPublishingStatus(final PublishingStatus publishingStatus);
}