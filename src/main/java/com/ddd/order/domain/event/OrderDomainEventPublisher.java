package com.ddd.order.domain.event;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.ddd.shared.core.event.DomainEvent;
import org.ddd.shared.core.event.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 14:18
 */
@Component
@Slf4j
public class OrderDomainEventPublisher implements DomainEventPublisher {


    private EventBus eventBus;

    private TaskExecutor taskExecutor;

    public OrderDomainEventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    @Autowired
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void publish(DomainEvent event) {
        log.info("publish event: {}", event);
        //示例，发布消息到总线、如果用MQ，这里改为MQ即可
        //为了不影响主业务， 开辟线程来发送领域事件
        taskExecutor.execute(()->eventBus.post(event));
    }
}
