package com.ecommerce.applicationmanager.services;


import com.ecommerce.applicationmanager.adapters.OrderAdapter;
import com.ecommerce.applicationmanager.models.Order;
import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceOrder {

    private ProducerService<Order> producerServiceOrder = new ProducerService<>();

    KafkaServiceEmail KafkaServiceEmail = new KafkaServiceEmail();
    KafkaServiceFraudDetector kafkaServiceFraudDetector = new KafkaServiceFraudDetector();

    public Order create(final Order order) {
        log.info("KafkaServiceOrder: create {}", order.getName());
        send(order);
        kafkaServiceFraudDetector.send(order);
        KafkaServiceEmail.send(order.getEmail());
        return order;
    }

    private void send(final Order order) {
        log.info(">> KafkaServiceOrder: send order {} to create new order", order.getName());
        Thread thread = new Thread(() -> producerServiceOrder.send("ECOMMERCE_NEW_ORDER", order.getCode(), order));
        thread.start();
    }

    public Order findByEmail(final String email) {
        log.info("KafkaServiceOrder: findByEmail {}", email);
        return OrderAdapter.createMock();
    }
}
