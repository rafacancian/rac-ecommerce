package com.ecommerce.producer;

import com.ecommerce.gson.MessageSerializer;
import com.ecommerce.model.Correlate;
import com.ecommerce.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class ProducerService<T> {

    private KafkaProducer<String, Message<T>> kafkaProducer;

    public ProducerService() {
        this.kafkaProducer = new KafkaProducer<>(getProperties());
    }

    public void send(String topic, String key, T payload) {
        var value = new Message<>(new Correlate(payload.getClass().getSimpleName()), payload);
        var producerRecord = new ProducerRecord<>(topic, key, value);
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
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        return properties;
    }
}
