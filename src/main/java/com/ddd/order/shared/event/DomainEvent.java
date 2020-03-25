package com.ddd.order.shared.event;

import com.ddd.order.infrastructure.utils.UuidGenerator;

import java.util.Date;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-25 11:17
 */
public abstract class DomainEvent {
    private final String id = UuidGenerator.newUuid();
    private final Date createAt = new Date();

    public String getId() {
        return id;
    }

    public Date getCreateAt() {
        return createAt;
    }
}
