package com.ecommerce.logger.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.model.Message;
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
                ConsumerLogger.class.getSimpleName(), ConsumerLogger::parse,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()));
        consumerService.execute();
    }

    public static void parse(final ConsumerRecord<String, Message<String>> record) {
        System.out.println(">> [LOG]: " + record.topic());
        System.out.println("Key: " + record.key() + " | Value:" + record.value());
    }
}
