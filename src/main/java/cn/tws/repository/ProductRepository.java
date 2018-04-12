package cn.tws.repository;

import cn.tws.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Orders, Long> {
}
