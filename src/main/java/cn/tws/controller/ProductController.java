package cn.tws.controller;

import cn.tws.entity.Inventory;
import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import cn.tws.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.valueOf;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Product product = productService.getProduct(valueOf(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Inventory inventory = new Inventory(0,0);

        product.setInventory(inventory);

        Product createdProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product input, @PathVariable("id") Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(!productOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();
        product.setDescription(input.getDescription());
        product.setName(input.getName());
        product.setPrice(input.getPrice());

        productRepository.save(product);

        return ResponseEntity.noContent().build();
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
