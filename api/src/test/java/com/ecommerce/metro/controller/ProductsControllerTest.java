package com.ecommerce.metro.controller;

import com.ecommerce.metro.application.SpringBootApplicationStarter;
import com.ecommerce.metro.entity.Product;
import com.ecommerce.metro.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringBootApplicationStarter.class, properties = {"spring.datasource.url="})
public class ProductsControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    @Test
    public void testGetAllProducts() throws Exception
    {
        Mockito.when(this.productService.getAllProduct())
                .thenReturn(Collections.singletonList(new Product(1, "P1",
                        "Produs 1", "5", "ImageP1")));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"P1\"," +
                        "\"description\":\"Produs 1\",\"price\":\"5\",\"image\":\"ImageP1\"}]"));
    }


    @Test
    public void testGetProductById() throws Exception
    {
        Mockito.when(this.productService.getProduct(1))
                .thenReturn(new Product(1, "P1", "Produs 1", "5", "ImageP1"));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"P1\"," +
                        "\"description\":\"Produs 1\",\"price\":\"5\",\"image\":\"ImageP1\"}"));
    }


    @Test
    public void testGetProductByIdAndNotFound() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("The product with ID 3 does not exist!"));
    }


    @Test
    public void testAddProduct() throws Exception
    {
        final String product =
                "{\"id\":1,\"name\":\"P1\",\"description\":\"Produs 1\",\"price\":\"5\",\"image\":\"ImageP1\"}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(product))
                .andExpect(status().isOk())
                .andExpect(content().string("Product added!"));
    }


    @Test
    public void testAddProductBadRequest() throws Exception
    {
        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(this.productService).addProduct(Mockito.isA(Product.class));

        final String product =
                "{\"id\":1,\"nae\":\"P1\",\"desiption\":\"Produs 1\",\"price\":\"5\",\"image\":\"ImageP1\"}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(product))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad request!"));
    }


    @Test
    public void testRemoveProduct() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("The product with ID 1 has been removed!"));
    }


    @Test
    public void testRemoveProductAndIdNotFound() throws Exception
    {
        Mockito.doThrow(EmptyResultDataAccessException.class)
                .when(this.productService).removeProduct(Mockito.isA(Integer.class));

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("The product with ID 1 does not exist!"));
    }
}
