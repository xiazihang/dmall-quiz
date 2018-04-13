package cn.tws.controller;

import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("uri", "/products/" + String.valueOf(createdProduct.getId()));

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Product product = productRepository.getById(Long.valueOf(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "description", required = false) String description) {

        if (name == null && description == null) {
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        }

        if (name == null && description != null) {
            return new ResponseEntity<>(productRepository.findByDescriptionContaining(description), HttpStatus.OK);
        }

        if (name != null && description == null) {
            return new ResponseEntity<>(productRepository.findByName(name), HttpStatus.OK);
        }

        return new ResponseEntity<>(productRepository.findByDescriptionContainingAndName(description, name), HttpStatus.OK);
    }
}
