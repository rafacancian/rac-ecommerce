package com.ecommerce.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerService {

    public static void execute() {
        var kafkaConsumer = new KafkaConsumer<String, String>(getProperties());
        kafkaConsumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
        while (true) {
            var records = kafkaConsumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
                System.out.println("Register founded");
                records.forEach(record -> {
                    System.out.println("----------------------");
                    System.out.println("Processing new order");
                    System.out.println(record.key());
                    System.out.println(record.value());
                });
            }
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaConsumerService.class.getSimpleName());
        return properties;

    }
}