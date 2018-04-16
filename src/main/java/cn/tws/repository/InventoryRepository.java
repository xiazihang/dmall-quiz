package cn.tws.repository;

import cn.tws.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductId(Long id);
}
