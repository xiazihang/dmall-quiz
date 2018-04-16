package cn.tws.controller;

import cn.tws.entity.*;
import cn.tws.repository.*;
import cn.tws.utils.LogisticsStatus;
import cn.tws.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @PostMapping(value = "")
    public ResponseEntity createOrder(@RequestBody List<HashMap> orderInfoArray) throws Exception {
        Orders orders = new Orders();
        orderRepository.save(orders);
        List<PurchaseItem> purchaseItemList = new ArrayList<>();
        for (HashMap orderInfo : orderInfoArray) {

            Integer productId = (Integer) orderInfo.get("productId");
            Integer purchaseCount = (Integer) orderInfo.get("purchaseCount");

            Product product = productRepository.findOne(Long.valueOf(productId));
            if (product == null) {
                 return new ResponseEntity<>("该订单中包含不存在的商品ID", HttpStatus.NOT_FOUND);
            }

            Inventory inventory = inventoryRepository.findByProductId(Long.valueOf(productId));
            if (purchaseCount > inventory.getLockedCount()) {
                 return new ResponseEntity<>("该订单中存在商品数量大于库存数量", HttpStatus.NOT_FOUND);
            } else {
                inventory.setLockedCount(inventory.getLockedCount() - purchaseCount);
                inventoryRepository.save(inventory);
            }
            purchaseItemList.add(createPurchaseItem(productId, purchaseCount, orders.getId()));

        }
        
        int totalPrice = getTotalPrice(purchaseItemList);

        orders.setTotalPrice(totalPrice);
        orders.setUserId(1);
        orders.setStatus(OrderStatus.unPaid);
        orders.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(orders);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    public PurchaseItem createPurchaseItem(Integer productId, Integer purchaseCount, Long orderId) {
        Product product = productRepository.findOne(Long.valueOf(productId));
        return purchaseItemRepository.save(new PurchaseItem(product.getId(), orderId, product.getName(), product.getDescription(), product.getPrice(), purchaseCount));
    }

    public int getTotalPrice(List<PurchaseItem> purchaseItemList) {
        return purchaseItemList.stream().map(purchaseItem -> purchaseItem.getPurchaseCount() * purchaseItem.getPurchasePrice()).reduce(0, (a, b) -> a + b);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity payOrder(@PathVariable Long id) throws Exception {
        Orders oldOrder = orderRepository.findOne(id);
        if(oldOrder == null){
            return new ResponseEntity<>("该订单不存在", HttpStatus.OK);
        }
        if (oldOrder.getStatus() == OrderStatus.paid) {
            return new ResponseEntity<>("您已支付成功，请勿重新支付", HttpStatus.OK);
        }
        if (oldOrder.getStatus() == OrderStatus.withdrawn) {
            return new ResponseEntity<>("您已经撤销了该订单，如有需要请重新发起一个订单", HttpStatus.OK);
        }
        if (oldOrder.getStatus() == OrderStatus.finished) {
            return new ResponseEntity<>("该订单已完成，不可再进行支付", HttpStatus.OK);
        }

        oldOrder.setStatus(OrderStatus.paid);
        oldOrder.setPaidTime(new Timestamp(System.currentTimeMillis()));

        LogisticsRecord logisticsRecord = new LogisticsRecord();
        logisticsRecord.setLogisticsStatus(LogisticsStatus.readyToShip);
        logisticsRecord.setDeliveryMan("李师傅");
        logisticsRecordRepository.save(logisticsRecord);

        oldOrder.setLogisticsRecord(logisticsRecord);
        orderRepository.save(oldOrder);

        return new ResponseEntity<>(orderRepository.findOne(id), HttpStatus.OK);
    }

    @PutMapping(value = "/withDrawn/{id}")
    public ResponseEntity withDrawnOrder(@PathVariable Long id) throws Exception {
        Orders order = orderRepository.findOne(id);
        if(order == null){
            return new ResponseEntity<>("该订单不存在", HttpStatus.OK);
        }
        if (order.getStatus() == OrderStatus.paid) {
            return new ResponseEntity<>("该订单已被支付，不可撤销", HttpStatus.OK);
        }
        if (order.getStatus() == OrderStatus.withdrawn) {
            return new ResponseEntity<>("该订单已被撤销，不可重复撤销", HttpStatus.OK);
        }
        if (order.getStatus() == OrderStatus.finished) {
            return new ResponseEntity<>("该订单已完成，不可撤销", HttpStatus.OK);
        }
        order.setStatus(OrderStatus.withdrawn);
        order.setWithdrawnTime(new Timestamp(System.currentTimeMillis()));

        List<PurchaseItem> purchaseItems = purchaseItemRepository.findByOrderId(id);
        for (PurchaseItem purchaseItem:purchaseItems){
            Inventory inventory = inventoryRepository.findByProductId(purchaseItem.getProductId());
            inventory.setLockedCount(inventory.getLockedCount() + purchaseItem.getPurchaseCount());

            orderRepository.save(order);
        }

        return new ResponseEntity<>(orderRepository.findOne(id), HttpStatus.OK);
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEntity findOrdersByUserId(@PathVariable Integer id) throws Exception {
        List<Orders> orders = orderRepository.findAllByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findAllInfoByOrderId(@PathVariable Long id) throws Exception {
        Orders orders = orderRepository.findOne(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
