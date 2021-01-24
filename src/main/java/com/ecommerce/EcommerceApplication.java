package com.ecommerce;

import com.ecommerce.kafka.consumers.email.ConsumerSendEmail;
import com.ecommerce.kafka.consumers.fraud.ConsumerFraudDetector;
import com.ecommerce.kafka.consumers.logger.ConsumerLogger;
import com.ecommerce.kafka.consumers.order.ConsumerCreateOrder;
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
