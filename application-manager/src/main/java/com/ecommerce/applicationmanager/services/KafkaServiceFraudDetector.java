package com.ecommerce.applicationmanager.services;

import com.ecommerce.applicationmanager.models.Order;
import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceFraudDetector {

    private ProducerService<Order> producerService = new ProducerService<>();

    public void send(final Order order) {
        log.info(">> KafkaServiceOrder: send order {} to fraud detector", order.getName());
        Thread thread = new Thread(() -> producerService.send("ECOMMERCE_FRAUD_DETECTOR", order.getCode(), order));
        thread.start();
    }

}
