package cn.tws.service;

import cn.tws.entity.Product;
import cn.tws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }
}
