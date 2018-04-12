package cn.tws.controller;

import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "")
    public ResponseEntity<Map<String,String>> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);

        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("uri","/products/"+String.valueOf(createdProduct.getId()));

        ResponseEntity<Map<String,String>> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        return responseEntity;
    }

}
