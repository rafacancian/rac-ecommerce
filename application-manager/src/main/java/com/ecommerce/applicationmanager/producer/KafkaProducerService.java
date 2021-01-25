package com.ecommerce.applicationmanager.producer;


import com.ecommerce.applicationmanager.adapters.EmailAdapter;
import com.ecommerce.applicationmanager.adapters.OrderAdapter;
import com.ecommerce.applicationmanager.models.Email;
import com.ecommerce.applicationmanager.models.Order;
import com.ecommerce.gson.GsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class KafkaProducerService {

    public static void execute() {

        final Order order = OrderAdapter.createMock();
        final Email email = EmailAdapter.createMock();

        sendOrder(order);
        sendFraudDetector(order);
        sendEmail(email);
    }

    private static void sendOrder(final Order order) {
        try (var kafkaProducerOrder = new KafkaProducer<String, Order>(getProperties())) {
            var recordCreateOrder = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", order.getCode(), order);
            kafkaProducerOrder.send(recordCreateOrder, getCallback()).get();
        } catch (ExecutionException | InterruptedException ex) {
            log.error("Error when try to produce kafka", ex);
        }
    }

    private static void sendFraudDetector(final Order order) {
        try (var kafkaProducerFraudDetector = new KafkaProducer<String, Order>(getProperties())) {
            var recordFraudDetector = new ProducerRecord<>("ECOMMERCE_FRAUD_DETECTOR", order.getCode(), order);
            kafkaProducerFraudDetector.send(recordFraudDetector, getCallback()).get();
        } catch (ExecutionException | InterruptedException ex) {
            log.error("Error when try to produce kafka", ex);
        }
    }

    private static void sendEmail(final Email email) {
        try (var kafkaProducerEmail = new KafkaProducer<String, Email>(getProperties())) {
            var recordSendEmail = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", email.getCode(), email);
            kafkaProducerEmail.send(recordSendEmail, getCallback()).get();
        } catch (ExecutionException | InterruptedException ex) {
            log.error("Error when try to produce kafka", ex);
        }
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
