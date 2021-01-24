package com.ecommerce.kafka.consumers.logger;

import com.ecommerce.kafka.consumers.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.regex.Pattern;

public class ConsumerLogger {

    private ConsumerLogger() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService(Pattern.compile("ECOMMERCE.*"),
                ConsumerLogger.class.getSimpleName(), ConsumerLogger::parse);
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> [LOG]: " + record.topic());
        System.out.println("Key: " + record.key() + " | Value:" + record.value());
    }
}
