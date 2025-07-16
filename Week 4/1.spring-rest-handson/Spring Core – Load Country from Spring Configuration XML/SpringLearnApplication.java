package com.cognizant.Ex2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {

    // Logger for the main application class
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        // Log the start of the main method
        LOGGER.debug("Starting SpringLearnApplication main method...");
        // Invoke the method to display country details
        displayCountry();
        // Log the end of the main method
        LOGGER.debug("Finished SpringLearnApplication main method.");
    }

    // Method to read the country bean from Spring configuration and display its details
    public static void displayCountry() {
        // Log the start of the displayCountry method
        LOGGER.debug("Starting displayCountry method...");

        // Create an ApplicationContext to load the Spring configuration from country.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // Retrieve the "country" bean from the context, casting it to Country type
        // This is where Spring instantiates the Country object and injects properties
        Country country = (Country) context.getBean("country", Country.class);

        // Log the details of the retrieved Country object using its toString() method
        LOGGER.debug("Country : {}", country.toString());

        // Log the end of the displayCountry method
        LOGGER.debug("Finished displayCountry method.");
    }
}
