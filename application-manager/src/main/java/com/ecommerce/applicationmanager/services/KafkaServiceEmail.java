package com.ecommerce.applicationmanager.services;

import com.ecommerce.applicationmanager.adapters.EmailAdapter;
import com.ecommerce.model.Email;
import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceEmail {

    private ProducerService<Email> producerServiceEmail = new ProducerService<>();

    public void send(final String subject) {
        final Email email = EmailAdapter.createMock(subject);
        log.info(">> KafkaServiceEmail: send email {} to email service", email.getSubject());
        Thread thread = new Thread(() -> producerServiceEmail.sendAsync("ECOMMERCE_SEND_EMAIL", email.getCode(), email));
        thread.start();
    }

}
