package cn.tws.repository;

import cn.tws.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
   PurchaseItem findByOrderId(Long id);
}
