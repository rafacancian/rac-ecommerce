package com.ecommerce.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class KafkaProducerService {

    public static void execute() {
        try (var kafkaProducer = new KafkaProducer<String, String>(getProperties())) {
            String value = getOrder();
            var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
            kafkaProducer.send(record, (data, ex) -> {
                if (ex == null) {
                    System.out.println("-----------------------------");
                    System.out.println("Message sent with success");
                    System.out.println("Topic: " + data.topic());
                    System.out.println("Partition:" + data.partition());
                }
            }).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static String getOrder() {
        String idUser = "01";
        String idProduct = "000111";
        String price = "54,00";
        return idUser + "," + idProduct + "," + price;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
