package com.cognizant.Ex2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    // Logger for this class to log debug messages
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    // Instance variables for country code and name
    private String code;
    private String name;

    // Empty constructor
    public Country() {
        // Log a debug message when the constructor is invoked
        LOGGER.debug("Inside Country Constructor.");
    }

    // Getter for the 'code' property
    public String getCode() {
        // Log a debug message when the getter is invoked
        LOGGER.debug("Inside Country Getter - code: {}", code);
        return code;
    }

    // Setter for the 'code' property
    public void setCode(String code) {
        // Log a debug message when the setter is invoked
        LOGGER.debug("Inside Country Setter - code: {}", code);
        this.code = code;
    }

    // Getter for the 'name' property
    public String getName() {
        // Log a debug message when the getter is invoked
        LOGGER.debug("Inside Country Getter - name: {}", name);
        return name;
    }

    // Setter for the 'name' property
    public void setName(String name) {
        // Log a debug message when the setter is invoked
        LOGGER.debug("Inside Country Setter - name: {}", name);
        this.name = name;
    }

    // Override toString() method to provide a string representation of the Country object
    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
