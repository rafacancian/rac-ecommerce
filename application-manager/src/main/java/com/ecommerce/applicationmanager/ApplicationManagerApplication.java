package com.ecommerce.applicationmanager;

import com.ecommerce.applicationmanager.producer.KafkaProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationManagerApplication.class, args);

        KafkaProducerService kafkaProducerService = new KafkaProducerService();
        kafkaProducerService.execute();
    }

}
