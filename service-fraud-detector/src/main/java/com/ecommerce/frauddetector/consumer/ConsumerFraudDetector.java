package com.ecommerce.frauddetector.consumer;

import com.ecommerce.consumer.consumers.ConsumerService;
import com.ecommerce.consumer.models.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class ConsumerFraudDetector {

    private ConsumerFraudDetector() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_FRAUD_DETECTOR",
                ConsumerFraudDetector.class.getSimpleName(), ConsumerFraudDetector::parse, Order.class, Map.of());
        consumerService.execute();
    }

    public static void parse(final ConsumerRecord<String, Order> record) {
        System.out.println(">> Fraud detector analyzed with success");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
    }
}
