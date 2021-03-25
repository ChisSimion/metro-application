package com.ecommerce.metro.service;

import com.ecommerce.metro.entity.Product;
import com.ecommerce.metro.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private final ProductRepository productRepository;


    public ProductService(final ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProduct()
    {
        return this.productRepository.findAll();
    }


    public Product getProduct(final Integer id)
    {
        return this.productRepository.findProductsById(id);
    }


    public void addProduct(final Product product)
    {
        this.productRepository.save(product);
    }


    public void removeProduct(final Integer id)
    {
        this.productRepository.deleteById(id);
    }
}
