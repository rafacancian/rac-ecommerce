package com.ecommerce.applicationmanager.services;


import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceReport {

    private ProducerService<String> producerServiceBatch = new ProducerService<>();

    public void generateReport() {
        log.info("KafkaServiceReport: generateReport");
        send();
    }

    private void send() {
        log.info(">> KafkaServiceReport: send generate reports");
        Thread thread = new Thread(() -> producerServiceBatch.sendAsync("ECOMMERCE_SEND_MESSAGE_ALL_USERS", "ECOMMERCE_USER_GENERATE_REPORTS", "ECOMMERCE_USER_GENERATE_REPORTS"));
        thread.start();
    }

}
