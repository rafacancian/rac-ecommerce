package com.ecommerce.applicationmanager.controllers;

import com.ecommerce.applicationmanager.services.KafkaServiceReport;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private KafkaServiceReport kafkaServiceReport;

    @GetMapping(value = "/generate")
    public ResponseEntity<String> generateReport() {
        kafkaServiceReport.generateReport();
        return ResponseEntity.ok().build();
    }
}
