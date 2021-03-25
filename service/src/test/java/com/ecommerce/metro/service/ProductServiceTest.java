package com.ecommerce.metro.service;

import com.ecommerce.metro.entity.Product;
import com.ecommerce.metro.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{
    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository productRepository;


    @Test
    public void testGetAllProducts()
    {
        Mockito.when(this.productRepository.findAll()).thenReturn( Arrays.asList(
                new Product(1, "P1", "Produs 1", "3", "ImageP1"),
                new Product(2, "P2", "Produs 2", "7", "ImageP2") ) );

        final List<Product> products = this.service.getAllProduct();

        Assert.assertNotNull(products);
        Assert.assertEquals(2, products.size());

        Assert.assertEquals(1, products.get(0).getId().intValue());
        Assert.assertEquals("P1", products.get(0).getName());
        Assert.assertEquals("Produs 1", products.get(0).getDescription());
        Assert.assertEquals("3", products.get(0).getPrice());
        Assert.assertEquals("ImageP1", products.get(0).getImage());

        Assert.assertEquals(2, products.get(1).getId().intValue());
        Assert.assertEquals("P2", products.get(1).getName());
        Assert.assertEquals("Produs 2", products.get(1).getDescription());
        Assert.assertEquals("7", products.get(1).getPrice());
        Assert.assertEquals("ImageP2", products.get(1).getImage());
    }


    @Test
    public void testGetProductById()
    {
        Mockito.when(this.productRepository.findProductsById(5))
                .thenReturn( new Product(5, "P5", "Produs 5", "10", "ImageP5") );

        final Product product = this.service.getProduct(5);

        Assert.assertNotNull(product);

        Assert.assertEquals(5, product.getId().intValue());
        Assert.assertEquals("P5", product.getName());
        Assert.assertEquals("Produs 5", product.getDescription());
        Assert.assertEquals("10", product.getPrice());
        Assert.assertEquals("ImageP5", product.getImage());
    }


    @Test
    public void testGetProductByIdAndNotFound()
    {
        final Product product = this.service.getProduct(5);
        Assert.assertNull(product);
    }


    @Test
    public void testAddProduct()
    {
        final Product product = new Product(1, "P1", "Produs 1", "5", "ImageP5");
        this.service.addProduct(product);
        Mockito.verify(this.productRepository).save(product);
    }


    @Test
    public void testDeleteProduct()
    {
        this.service.removeProduct(3);
        Mockito.verify(this.productRepository).deleteById(3);
    }
}
