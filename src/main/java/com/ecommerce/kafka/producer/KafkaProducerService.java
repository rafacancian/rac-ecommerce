package com.ecommerce.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class KafkaProducerService {

    public static void execute() {

        try (var kafkaProducer = new KafkaProducer<String, String>(getProperties())) {

            Callback callback = (data, ex) -> {
                if (ex == null) {
                    System.out.print("### Message sent with success | ");
                    System.out.print("Topic: " + data.topic());
                    System.out.print("Partition:" + data.partition());
                }
            };

            final String value = getOrder();
            var recordCreateOrder = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
            kafkaProducer.send(recordCreateOrder, callback).get();

            final String fraudDetectorReport = getFraudDetectorReport();
            var recordFraudDetector = new ProducerRecord<>("ECOMMERCE_FRAUD_DETECTOR", fraudDetectorReport, fraudDetectorReport);
            kafkaProducer.send(recordFraudDetector, callback).get();

            final String email = getEmail();
            var recordSendEmail = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", email, email);
            kafkaProducer.send(recordSendEmail, callback).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static String getOrder() {
        String idUser = "01,";
        String idProduct = "000111,";
        String price = "54,00";
        return idUser + idProduct + price;
    }

    private static String getFraudDetectorReport() {
        return "Fraud Detector analyzer";
    }

    private static String getEmail() {
        return "kakfa_cancian@gmail.com";
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
