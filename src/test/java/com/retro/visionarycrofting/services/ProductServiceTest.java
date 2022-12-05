package com.retro.visionarycrofting.services;

import com.retro.visionarycrofting.entities.Product;
import com.retro.visionarycrofting.repositories.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductDao productDao;

    @Test
    void existsByRef() {
        Product product = new Product();
        product.setQuantity(12);
        product.setRef("ref1");
        product.setPrix(123);
        product.setDescription("desc");
        product.setName("p1");
        productDao.save(product);
        boolean expected = productDao.existsByRef("ref1");
        assertThat(expected).isTrue();
    }
}