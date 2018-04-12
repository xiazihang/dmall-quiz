package cn.tws.controller;

import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "")
    public void createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);

//        ResponseEntity<Product> responseBody = createdProduct
    }

}
