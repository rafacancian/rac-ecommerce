package com.ecommerce.kafka.consumers.logger;

import com.ecommerce.kafka.consumers.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

public class ConsumerLogger {

    private ConsumerLogger() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService(Pattern.compile("ECOMMERCE.*"),
                ConsumerLogger.class.getSimpleName(), ConsumerLogger::parse, String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()));
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> [LOG]: " + record.topic());
        System.out.println("Key: " + record.key() + " | Value:" + record.value());
    }
}
