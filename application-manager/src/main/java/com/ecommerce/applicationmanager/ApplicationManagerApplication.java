package com.ecommerce.applicationmanager;

import com.ecommerce.applicationmanager.adapters.OrderAdapter;
import com.ecommerce.applicationmanager.services.KafkaServiceOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationManagerApplication.class, args);

        KafkaServiceOrder kafkaServiceOrder = new KafkaServiceOrder();
        kafkaServiceOrder.create(OrderAdapter.createMock());
    }

}
