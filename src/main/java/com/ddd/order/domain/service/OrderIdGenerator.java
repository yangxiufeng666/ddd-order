package com.ddd.order.domain.service;

import com.ddd.order.infrastructure.common.DomainService;
import com.ddd.order.infrastructure.utils.UuidGenerator;
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
