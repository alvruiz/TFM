package com.viu.patronAPP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PatronAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatronAppApplication.class, args);
    }

}
