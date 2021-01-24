package com.ecommerce.kafka.producer;

import com.ecommerce.kafka.adapters.OrderAdapter;
import com.ecommerce.kafka.gson.GsonSerializer;
import com.ecommerce.kafka.models.Order;
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

        try (var kafkaProducerOrder = new KafkaProducer<String, Order>(getProperties())) {
            //try (var kafkaProducerEmail = new KafkaProducer<String, String>(getProperties())) {

            Callback callback = (data, ex) -> {
                if (ex == null) {
                    System.out.println("Message sent with success");
                    System.out.println("Topic: " + data.topic() + " | Partition:" + data.partition());
                }
            };

            final Order order = OrderAdapter.createMock();
            var recordCreateOrder = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", order.getCode(), order);
            kafkaProducerOrder.send(recordCreateOrder, callback).get();

//            final String fraudDetectorReport = "Fraud Detector analyzer";
//            var recordFraudDetector = new ProducerRecord<>("ECOMMERCE_FRAUD_DETECTOR", fraudDetectorReport, fraudDetectorReport);
//            kafkaProducer.send(recordFraudDetector, callback).get();

//            final User user = UserAdater.createMock();
//            var recordSendEmail = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", user.getEmail(), user.getEmail());
//            kafkaProducerEmail.send(recordSendEmail, callback).get();

        } catch (ExecutionException | InterruptedException ex) {
            log.error("Error when try to produce kafka", ex);
        }
        // }
    }


    private static Properties getProperties() {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }
}
