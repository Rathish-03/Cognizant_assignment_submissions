package com.cognizant.spring_learn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootTest
class SpringLearnApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	@Test
	void contextLoads() {
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);
		LOGGER.info("SpringLearnApplication started successfully!");

	}

}
