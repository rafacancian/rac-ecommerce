package com.ecommerce.order.producer;

import com.ecommerce.kafka.gson.GsonSerializer;
import com.ecommerce.order.adapters.OrderAdapter;
import com.ecommerce.order.models.Order;
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


            Callback callback = (data, ex) -> {
                if (ex == null) {
                    System.out.println("Message sent with success");
                    System.out.println("Topic: " + data.topic() + " | Partition:" + data.partition());
                }
            };

            final Order order = OrderAdapter.createMock();
            var recordCreateOrder = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", order.getCode(), order);
            kafkaProducerOrder.send(recordCreateOrder, callback).get();

        } catch (ExecutionException | InterruptedException ex) {
            log.error("Error when try to produce kafka", ex);
        }

    }


    private static Properties getProperties() {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }
}
