package com.ecommerce.order;

import com.ecommerce.order.consumer.ConsumerCreateOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);

        ConsumerCreateOrder.execute();
    }

}
