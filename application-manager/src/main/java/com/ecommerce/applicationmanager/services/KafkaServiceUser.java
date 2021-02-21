package com.ecommerce.applicationmanager.services;


import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceUser {

    private ProducerService<String> producerServiceUser = new ProducerService<>();

    public void send(final String email) {
        log.info(">> KafkaServiceUser: send user {} to validate user", email);
        Thread thread = new Thread(() -> producerServiceUser.sendAsync("ECOMMERCE_USER_CHECK", email, email));
        thread.start();
    }

}
