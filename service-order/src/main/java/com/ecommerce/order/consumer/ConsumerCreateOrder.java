package com.ecommerce.order.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.model.Message;
import com.ecommerce.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class ConsumerCreateOrder {

    private ConsumerCreateOrder() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService<Order>("ECOMMERCE_NEW_ORDER",
                ConsumerCreateOrder.class.getSimpleName(), ConsumerCreateOrder::parse, Map.of());
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, Message<Order>> record) {
        System.out.println(">> Order created with success");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
    }

}
