package com.ddd.order.infrastructure.event;

import com.ddd.order.domain.event.OrderDomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 14:17
 */
@Component
@Aspect
@Slf4j
public class DomainEventPublishAspect {

    private OrderDomainEventPublisher publisher;

    private TaskExecutor taskExecutor;

    public DomainEventPublishAspect(OrderDomainEventPublisher publisher, TaskExecutor taskExecutor) {
        this.publisher = publisher;
        this.taskExecutor = taskExecutor;
    }

    @After("@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ")
    public void publishEvents(JoinPoint joinPoint) {
        log.info("Trigger domain event publish process.");
    }
}
