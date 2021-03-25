package com.ecommerce.metro.repository;

import com.ecommerce.metro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
    List<Product> findAll();

    Product findProductsById(final Integer id);

    void deleteById(final Integer id);
}
