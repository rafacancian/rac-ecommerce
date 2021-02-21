package com.ecommerce.frauddetector.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.frauddetector.validate.ValidateFraud;
import com.ecommerce.model.Message;
import com.ecommerce.model.Order;
import com.ecommerce.producer.ProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class ConsumerFraudDetector {

    private ProducerService<Order> producerService = new ProducerService<>();

    public void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_FRAUD_DETECTOR",
                ConsumerFraudDetector.class.getSimpleName(), this::parse, Map.of());
        consumerService.execute();
    }

    public void parse(final ConsumerRecord<String, Message<Order>> record) {
        System.out.println(">> Fraud detector analyzed with success");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());

        var order = record.value().getPayload();
        if (ValidateFraud.checkIsFraud(order)) {
            System.out.println("Is Fraud");
            producerService.sendAsync("ECOMMERCE_ORDER_REJECTED", order.getEmail(), order);
        } else {
            System.out.println("Is not Fraud");
            producerService.sendAsync("ECOMMERCE_ORDER_APPROVED", order.getEmail(), order);
        }
    }


}
