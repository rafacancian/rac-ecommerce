package com.ecommerce.kafka.consumers;

import com.ecommerce.kafka.consumers.email.ConsumerSendEmail;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@AllArgsConstructor
public class ConsumerService {

    KafkaConsumer<String, String> kafkaConsumer;
    ConsumerFunction consumerFactory;

    public ConsumerService(String topic, ConsumerFunction parse) {
        kafkaConsumer = new KafkaConsumer<>(getProperties());
        consumerFactory = parse;
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }

    public void execute() {
        Thread thread = new Thread(() -> {
            while (true) {
                var records = kafkaConsumer.poll(Duration.ofSeconds(1));
                if (!records.isEmpty()) {
                    records.forEach(record -> consumerFactory.parse(record));
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
