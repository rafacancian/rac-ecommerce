package com.ecommerce.servicereporting;

import com.ecommerce.servicereporting.consumer.ReadingReportDetector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceReportingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceReportingApplication.class, args);

        ReadingReportDetector readingReportDetector = new ReadingReportDetector();
        readingReportDetector.execute();
    }

}
