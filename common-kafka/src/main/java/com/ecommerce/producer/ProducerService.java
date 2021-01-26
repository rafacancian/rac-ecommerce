package com.ecommerce.producer;

import com.ecommerce.gson.GsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerService<T> {

    private KafkaProducer<String, T> kafkaProducer;

    public ProducerService() {
        this.kafkaProducer = new KafkaProducer<>(getProperties());
    }

    public void send(String topic, String key, T type) {
        var producerRecord = new ProducerRecord<>(topic, key, type);
        kafkaProducer.send(producerRecord, getCallback());
    }

    private static Callback getCallback() {
        return (data, ex) -> {
            if (ex == null) {
                System.out.println("Message sent with success");
                System.out.println("Topic: " + data.topic() + " | Partition:" + data.partition());
            }
        };
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }

}
