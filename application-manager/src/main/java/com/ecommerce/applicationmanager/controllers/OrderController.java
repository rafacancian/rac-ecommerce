package com.ecommerce.applicationmanager.controllers;

import com.ecommerce.applicationmanager.services.KafkaServiceOrder;
import com.ecommerce.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private KafkaServiceOrder kafkaServiceOrder;

    @GetMapping(value = "/search")
    public ResponseEntity<Order> findByEmail(@RequestParam final String email) {
        final Order order = kafkaServiceOrder.findByEmail(email);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody final Order order) {
        kafkaServiceOrder.create(order);
        return ResponseEntity.ok(order);
    }
}
