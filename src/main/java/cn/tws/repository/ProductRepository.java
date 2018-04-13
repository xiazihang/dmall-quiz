package cn.tws.repository;

import cn.tws.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product getById(Long id);
    List<Product> findAll();
    List<Product> findByDescriptionContaining(String desc);
    List<Product> findByDescriptionContainingAndName(String desc,String name);
    List<Product> findByName(String name);
}
