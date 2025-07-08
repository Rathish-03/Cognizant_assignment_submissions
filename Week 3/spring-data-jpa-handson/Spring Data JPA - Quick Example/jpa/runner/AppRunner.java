package com.example.jpa.runner;

import com.example.jpa.entity.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    public AppRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));

        userRepository.findAll().forEach(user ->
            System.out.println("User: " + user.getId() + " - " + user.getName()));
    }
}

