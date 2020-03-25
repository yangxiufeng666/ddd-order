package com.ddd.order.shared.event;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 14:44
 */
public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
