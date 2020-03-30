package com.ddd.order.domain.service;

import org.ddd.shared.core.DomainService;
import org.ddd.shared.core.utils.UuidGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 14:18
 */
@Component
public class OrderIdGenerator implements DomainService {

    public String generate(){
        return UuidGenerator.newUuid();
    }
}
