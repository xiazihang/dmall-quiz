package cn.tws.repository;

import cn.tws.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUserId(Integer userId);
    Orders findByLogisticsRecordId(Long id);
}
