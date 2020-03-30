package com.ddd.order.domain.repository;



import org.ddd.shared.core.event.DomainEvent;

import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 14:08
 */
public class DomainEventRepository {
    public void save(List<DomainEvent> events) {

    }

    public void delete(String eventId) {

    }

    public DomainEvent get(String eventId) {
        return null;
    }

    public List<DomainEvent> nextPublishBatch(int size) {
        return null;
    }

    public void markAsPublished(String eventId) {

    }

    public void markAsPublishFailed(String eventId) {

    }

    public void deleteAll() {

    }
}
