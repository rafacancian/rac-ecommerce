package com.ecommerce.email.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.model.Email;
import com.ecommerce.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class ConsumerSendEmail {

    private ConsumerSendEmail() {
    }

    public static void execute() {
        ConsumerService consumerService = new ConsumerService("ECOMMERCE_SEND_EMAIL",
                ConsumerSendEmail.class.getSimpleName(), ConsumerSendEmail::parse, Map.of());
        consumerService.execute();
    }

    public static void parse(final ConsumerRecord<String, Message<Email>> record) {
        System.out.println(">> Email sent with success");
        System.out.println("Key: " + record.key() + " | Value: " + record.value());
    }
}
