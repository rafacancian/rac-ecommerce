package com.ecommerce.applicationmanager.producer;


import com.ecommerce.applicationmanager.adapters.EmailAdapter;
import com.ecommerce.applicationmanager.adapters.OrderAdapter;
import com.ecommerce.applicationmanager.models.Email;
import com.ecommerce.applicationmanager.models.Order;
import com.ecommerce.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaProducerService {

    private ProducerService<Order> producerServiceOrder = new ProducerService<>();
    private ProducerService<Email> producerServiceEmail = new ProducerService<>();

    public void execute() {

        final Order order = OrderAdapter.createMock();
        final Email email = EmailAdapter.createMock();

        System.out.println("-----------------------------");
        System.out.println("Sending order");
        sendOrder(order);
        System.out.println("Sending fraud detector");
        sendFraudDetector(order);
        System.out.println("Sending email");
        sendEmail(email);
        System.out.println("-----------------------------");

    }

    private void sendOrder(final Order order) {
        Thread thread = new Thread(() -> producerServiceOrder.send("ECOMMERCE_NEW_ORDER", order.getCode(), order));
        thread.start();
    }

    private void sendFraudDetector(final Order order) {
        Thread thread = new Thread(() -> producerServiceOrder.send("ECOMMERCE_FRAUD_DETECTOR", order.getCode(), order));
        thread.start();
    }

    private void sendEmail(final Email email) {
        Thread thread = new Thread(() -> producerServiceEmail.send("ECOMMERCE_SEND_EMAIL", email.getCode(), email));
        thread.start();
    }


}
