package com.ecommerce.kafka.consumers.fraud;

import com.ecommerce.kafka.consumers.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerFraudDetector {

    private ConsumerFraudDetector() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_FRAUD_DETECTOR", ConsumerFraudDetector::parse);
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> Fraud detector analyzed with success");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
    }
}
