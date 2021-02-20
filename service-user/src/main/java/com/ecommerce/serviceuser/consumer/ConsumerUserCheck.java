package com.ecommerce.serviceuser.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.model.Message;
import com.ecommerce.model.User;
import com.ecommerce.producer.ProducerService;
import com.ecommerce.serviceuser.services.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Service
public class ConsumerUserCheck {

    private UserService userService = new UserService();

    private ProducerService<User> producerService = new ProducerService<>();

    public void execute() {
        ConsumerService consumerService = new ConsumerService<String>("ECOMMERCE_USER_CHECK",
                ConsumerUserCheck.class.getSimpleName(), this::parse, String.class, Map.of());
        consumerService.execute();
    }

    public void parse(ConsumerRecord<String, Message<String>> record) {
        System.out.println(">> Find user returned");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
        User user = userService.findUserByEmail(record.value().getPayload());

        if (ObjectUtils.isEmpty(user)) {
            System.out.println("User not allow to create an order");
            producerService.send("ECOMMERCE_USER_APPROVED", user.getEmail(), user);
        } else {
            System.out.println("User allow to create an order");
            producerService.send("ECOMMERCE_USER_REJECTED", user.getEmail(), user);
        }
    }

}
