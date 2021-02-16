package com.ecommerce.serviceuser;

import com.ecommerce.serviceuser.consumer.ConsumerReport;
import com.ecommerce.serviceuser.consumer.ConsumerUserCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);

        ConsumerUserCheck consumerUserCheck = new ConsumerUserCheck();
        consumerUserCheck.execute();

        ConsumerReport consumerReport = new ConsumerReport();
        consumerReport.execute();
    }

}
