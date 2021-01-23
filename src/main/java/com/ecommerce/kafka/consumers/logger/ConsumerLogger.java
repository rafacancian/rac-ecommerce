package com.ecommerce.kafka.consumers.logger;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerLogger {

    public static void execute() {

        var kafkaConsumer = new KafkaConsumer<String, String>(getProperties());
        kafkaConsumer.subscribe(Pattern.compile("ECOMMERCE.*"));

        Thread thread = new Thread(() -> {
            System.out.println("Thread of ConsumerLogger");
            while (true) {
                var records = kafkaConsumer.poll(Duration.ofSeconds(1));
                if (!records.isEmpty()) {
                    records.forEach(record -> {
                        System.out.println(">> [LOG]: " + record.topic());
                        System.out.println("Key: " + record.key() + "| Value:" + record.value());
                    });
                }
            }
        });
        thread.start();
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, ConsumerLogger.class.getSimpleName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}
