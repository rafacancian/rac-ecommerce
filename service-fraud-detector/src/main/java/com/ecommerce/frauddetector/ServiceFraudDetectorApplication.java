package com.ecommerce.frauddetector;

import com.ecommerce.frauddetector.consumer.ConsumerFraudDetector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceFraudDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFraudDetectorApplication.class, args);

        ConsumerFraudDetector consumerFraudDetector = new ConsumerFraudDetector();
        consumerFraudDetector.execute();
    }

}
