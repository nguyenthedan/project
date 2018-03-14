package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringBlogMvcApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBlogMvcApp.class, args);
    }
}
