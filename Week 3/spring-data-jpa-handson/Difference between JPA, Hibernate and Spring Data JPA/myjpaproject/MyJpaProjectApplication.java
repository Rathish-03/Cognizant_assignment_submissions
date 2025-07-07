package com.example.myjpaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation does a lot:
                       // 1. @Configuration: Tags the class as a source of bean definitions for the application context.
                       // 2. @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
                       // 3. @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'com.example.myjpaproject' package, allowing it to find your controllers, services, and repositories.
public class MyJpaProjectApplication {

    public static void main(String[] args) {
        // This is the main method that runs the Spring Boot application.
        // It delegates to Spring Boot's SpringApplication class.
        SpringApplication.run(MyJpaProjectApplication.class, args);
    }

}
