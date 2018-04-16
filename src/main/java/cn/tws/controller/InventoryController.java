package cn.tws.controller;

import cn.tws.entity.Inventory;
import cn.tws.entity.Product;
import cn.tws.repository.InventoryRepository;
import cn.tws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PutMapping("/{productId}")
    ResponseEntity<?> updateInventoriesCount(@PathVariable("productId") Long productId ,@RequestBody Inventory input){
        Optional<Product> productOptional = productRepository.findById(productId);

        if(!productOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Inventory inventory = productOptional.get().getInventory();

        inventory.setCount(inventory.getCount() + input.getCount());
        inventory.setLockedCount(inventory.getLockedCount() + input.getCount());

        inventoryRepository.save(inventory);
        return ResponseEntity.noContent().build();
    }
}
