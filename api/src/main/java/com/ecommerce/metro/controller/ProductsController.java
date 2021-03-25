package com.ecommerce.metro.controller;

import com.ecommerce.metro.entity.Product;
import com.ecommerce.metro.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "*")
public class ProductsController
{
    private final ProductService productService;


    public ProductsController(final ProductService productService)
    {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getAllProduct());
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") final Integer id)
    {
        final Product product = this.productService.getProduct(id);
        final Object responseBody = product != null ? this.productService.getProduct(id)
                : String.format("The product with ID %s does not exist!", id);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody final Product product)
    {
        try
        {
            this.productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product added!");
        }
        catch (final DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request!");
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") final Integer id)
    {
        String responseBody;
        try
        {
            this.productService.removeProduct(id);
            responseBody = "The product with ID " + id + " has been removed!";
        }
        catch (final EmptyResultDataAccessException e)
        {
            responseBody = "The product with ID " + id + " does not exist!";
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
