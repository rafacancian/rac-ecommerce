package com.ecommerce.kafka.consumers.order;

import com.ecommerce.kafka.consumers.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerCreateOrder {

    private ConsumerCreateOrder() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_NEW_ORDER", ConsumerCreateOrder::parse);
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> Order created with success");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
    }

}
