package com.ecommerce;

import com.ecommerce.kafka.consumer.email.ConsumerSendEmail;
import com.ecommerce.kafka.consumer.fraud.ConsumerFraudDetector;
import com.ecommerce.kafka.consumer.logger.ConsumerLogger;
import com.ecommerce.kafka.consumer.order.ConsumerCreateOrder;
import com.ecommerce.kafka.producer.KafkaProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);

        //PRODUCER
        KafkaProducerService.execute();

        //CONSUMERS
        ConsumerCreateOrder.execute();
        ConsumerFraudDetector.execute();
        ConsumerSendEmail.execute();
        ConsumerLogger.execute();

    }

}
