package cn.tws.repository;

import cn.tws.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
   List<PurchaseItem> findByOrderId(Long id);
}
