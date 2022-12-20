package com.example.springselectshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSelectShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSelectShopApplication.class, args);
    }

}
