package com.example; // Note: This is in the base 'com.example' package

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void createProduct(String id, String name, double price) {
        logger.info("Attempting to create product: ID={}, Name={}, Price={}", id, name, price);
        if (id == null || id.trim().isEmpty()) {
            logger.error("Product creation failed: Product ID cannot be empty or null.");
            throw new IllegalArgumentException("Product ID cannot be empty or null");
        }
        if (name == null || name.trim().isEmpty()) {
            logger.error("Product creation failed for ID {}: Product Name cannot be empty or null.", id);
            throw new IllegalArgumentException("Product Name cannot be empty or null");
        }
        if (price <= 0) {
            logger.warn("Product creation for ID {} with non-positive price: {}. Consider reviewing.", id, price);
            throw new IllegalArgumentException("Product price must be positive");
        }
        // Simulate product creation
        logger.debug("Product {} created successfully with price {}.", name, price);
        System.out.println("Product Created: ID=" + id + ", Name=" + name + ", Price=" + price);
    }

    public void processOrder(String orderId) {
        logger.info("Processing order: {}", orderId);
        // Simulate some processing logic
        if (Math.random() < 0.2) { // Simulate a 20% chance of error
            logger.error("Failed to process order {}: Database connection lost.", orderId);
            // In a real scenario, you might throw an exception here
        } else if (Math.random() < 0.5) { // Simulate a 30% chance of warning
            logger.warn("Order {} processed with warnings: Insufficient stock for some items.", orderId);
        } else {
            logger.debug("Order {} processed successfully.", orderId);
        }
        System.out.println("Order Processed: " + orderId);
    }

    public static void main(String[] args) {
        logger.info("Starting ProductService application.");
        ProductService service = new ProductService();

        System.out.println("\n--- Test Case 1: Valid Product ---");
        service.createProduct("P001", "Laptop", 1200.0);

        System.out.println("\n--- Test Case 2: Invalid Price ---");
        try {
            service.createProduct("P002", "Mouse", -10.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            logger.error("Caught expected exception during invalid price test: {}", e.getMessage());
        }

        System.out.println("\n--- Test Case 3: Empty Product ID ---");
        try {
            service.createProduct("", "Keyboard", 50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            logger.error("Caught expected exception during empty ID test: {}", e.getMessage());
        }

        System.out.println("\n--- Test Case 4: Process Order (with potential error/warning) ---");
        for (int i = 1; i <= 5; i++) {
            service.processOrder("ORD" + i);
        }
        logger.info("ProductService application finished.");
    }
}
