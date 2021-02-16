package com.ecommerce.serviceuser.consumer;

import com.ecommerce.consumer.ConsumerService;
import com.ecommerce.producer.ProducerService;
import com.ecommerce.serviceuser.models.User;
import com.ecommerce.serviceuser.services.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class ConsumerReport {

    private UserService userService = new UserService();

    private ProducerService<User> producerService = new ProducerService<>();

    public void execute() {
        ConsumerService consumerService = new ConsumerService<String>("SEND_MESSAGE_ALL_USERS",
                ConsumerReport.class.getSimpleName(), this::parse, String.class, Map.of());
        consumerService.execute();
    }

    public void parse(ConsumerRecord<String, String> record) {
        System.out.println(">> Processing batch send message all users");
        System.out.println("Key: " + record.key() + "| Value:" + record.value());
        List<User> users = userService.findAllUsers();

        if (!ObjectUtils.isEmpty(users)) {
            System.out.println("Numbers of user founded: " + users.size());
            for (User user : users) {
                producerService.send(record.value(), user.getEmail(), user);
            }
        }
    }


}
