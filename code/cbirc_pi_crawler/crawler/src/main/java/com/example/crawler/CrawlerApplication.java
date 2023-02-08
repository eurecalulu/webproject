package com.example.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

    //visit http://127.0.0.1:8080/services/ws/api?wsdl
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }

}
