package com.ecommerce.email.consumer;

import com.ecommerce.kafka.consumers.ConsumerService;
import com.ecommerce.kafka.models.Email;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class ConsumerSendEmail {

    private ConsumerSendEmail() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_SEND_EMAIL",
                ConsumerSendEmail.class.getSimpleName(), ConsumerSendEmail::parse, Email.class, Map.of());
        consumerService.execute();
    }

    public static void parse(final ConsumerRecord<String, Email> record) {
        System.out.println(">> Email sent with success");
        System.out.println("Key: " + record.key() + " | Value: " + record.value());
    }
}
