package com.example.myjpaproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // From Lombok: Generates getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Lombok annotation for boilerplate code
@NoArgsConstructor // Lombok annotation for no-arg constructor
@AllArgsConstructor // Lombok annotation for all-arg constructor
public class Product {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id;
    private String name;
    private double price;
    private int quantity;

    // If not using Lombok, you'd manually add:
    // public Product() {}
    // public Product(Long id, String name, double price, int quantity) { ... }
    // public Long getId() { ... }
    // public void setId(Long id) { ... }
    // ... and so on for all fields
}
