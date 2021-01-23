package com.ecommerce.kafka.consumers.email;

import com.ecommerce.kafka.consumers.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerSendEmail {

    private ConsumerSendEmail() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_SEND_EMAIL", ConsumerSendEmail::parse);
        consumerService.execute();
    }

    public static void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> Email sent with success");
        System.out.println("Key: " + record.key() + " | Value: " + record.value());
    }
}
