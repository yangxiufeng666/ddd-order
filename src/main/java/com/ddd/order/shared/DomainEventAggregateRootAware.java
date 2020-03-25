package com.ddd.order.shared;

import com.ddd.order.shared.event.DomainEvent;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 11:16
 */
public abstract class DomainEventAggregateRootAware extends AggregateRoot {
    private static final long serialVersionUID = 1L;
    private List<DomainEvent> events = Lists.newArrayList();

    protected void raiseEvent(DomainEvent event){
        this.events.add(event);
    }

    public List<DomainEvent> getEvents() {
        return Collections.synchronizedList(events);
    }
}
