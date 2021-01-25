package com.ecommerce.frauddetector;

import com.ecommerce.frauddetector.producer.KafkaProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceFraudDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFraudDetectorApplication.class, args);

        KafkaProducerService.execute();
    }

}
