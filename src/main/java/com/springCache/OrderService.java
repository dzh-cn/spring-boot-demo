package com.springCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * service
 *
 * @author: dongzhihua
 * @time: 2018/10/25 12:07:04
 */
@Component
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Cacheable("getOrderBean")
    public OrderBean getOrderBean(Integer id) throws InterruptedException {
        Thread.sleep(2000);
        logger.info("********** getOrderBean id: {}", id);
        return new OrderBean(id, "Number: " + id);
    }
}
