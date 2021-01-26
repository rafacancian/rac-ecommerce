package com.ecommerce.serviceuser.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.producer.ProducerService;
import com.ecommerce.serviceuser.models.User;
import com.ecommerce.serviceuser.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@AllArgsConstructor
public class ConsumerUserCheck {

    private UserService userService;
    private ProducerService<User> producerService;

    public void execute() {
        ConsumerService consumerService = new ConsumerService<String>("ECOMMERCE_USER_CHECK",
                ConsumerUserCheck.class.getSimpleName(), this::parse, String.class, Map.of());
        consumerService.execute();
    }

    public void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> Find user returned");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
        User user = userService.findUserByEmail(record.value());

        if (ObjectUtils.isEmpty(user)) {
            System.out.println("User not allow to create an order");
            producerService.send("ECOMMERCE_USER_APPROVED", user.getEmail(), user);
        } else {
            System.out.println("User allow to create an order");
            producerService.send("ECOMMERCE_USER_REJECTED", user.getEmail(), user);
        }
    }


}
