package com.ecommerce.kafka.consumers.email;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerSendEmail {

    public static void execute() {

        var kafkaConsumer = new KafkaConsumer<String, String>(getProperties());
        kafkaConsumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL"));

        Thread thread = new Thread(() -> {
            System.out.println("Thread of ConsumerSendEmail");
            while (true) {
                var records = kafkaConsumer.poll(Duration.ofSeconds(1));
                if (!records.isEmpty()) {
                    records.forEach(record -> {
                        System.out.println(">> Email sent with success");
                        System.out.println(record.key());
                        System.out.println(record.value());
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
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, ConsumerSendEmail.class.getSimpleName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}
