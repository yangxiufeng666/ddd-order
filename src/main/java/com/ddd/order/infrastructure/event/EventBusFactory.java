package com.ddd.order.infrastructure.event;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 15:42
 */
@Configuration
public class EventBusFactory {
    @Bean
    public EventBus configEvent() {
        EventBus eventBus = new EventBus();
        return eventBus;
    }
}
