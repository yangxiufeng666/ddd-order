package com.ddd.order.domain.event.consumer;

import com.ddd.order.domain.event.OrderCreatedEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 15:48
 */
@Component
@Slf4j
public class OrderCreatedListener {

    private EventBus eventBus;

    public OrderCreatedListener(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }
    @Subscribe
    public void orderCreated(OrderCreatedEvent orderCreatedEvent){
        log.info("consumer orderCreated : {}", orderCreatedEvent);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
