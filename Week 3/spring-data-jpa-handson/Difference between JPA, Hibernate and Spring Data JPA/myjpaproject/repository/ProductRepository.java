package com.example.myjpaproject.repository;

import com.example.myjpaproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional, but good practice to indicate it's a repository component
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository provides methods like:
    // save(entity)
    // findById(id)
    // findAll()
    // deleteById(id)
    // count()
    // ...and many more

    // You can also define custom query methods just by naming convention:
    Product findByName(String name);
    // List<Product> findByPriceGreaterThan(double price);
}
