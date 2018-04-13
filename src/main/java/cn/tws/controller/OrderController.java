package cn.tws.controller;

import cn.tws.entity.Orders;
import cn.tws.entity.Product;
import cn.tws.entity.PurchaseItem;
import cn.tws.repository.OrderRepository;
import cn.tws.repository.ProductRepository;
import cn.tws.repository.PurchaseItemRepository;
import cn.tws.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @PostMapping(value = "/orders")
    ResponseEntity createOrder(@RequestBody List<HashMap> orderInfoArray) throws Exception {
        Orders orders = new Orders();
        List<PurchaseItem> purchaseItemList = orderInfoArray.stream().map(orderInfo -> {
            String productId = orderInfo.get("productId").toString();
            String purchaseCount = orderInfo.get("purchaseCount").toString();
            return createPurchaseItem(productId, purchaseCount, orders);
        }).collect(Collectors.toList());

        int totalPrice = getTotalPrice(purchaseItemList);

        orders.setTotalPrice(totalPrice);
        orders.setStatus(OrderStatus.UNPAID);
        orders.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(orders);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    public PurchaseItem createPurchaseItem(String productId, String purchaseCount, Orders orders) {
        Product product = productRepository.findOne(Long.parseLong(productId));
        int count = Integer.parseInt(purchaseCount);
        return purchaseItemRepository.save(new PurchaseItem(product.getId(), product.getName(), product.getDescription(), product.getPrice(), count, orders));
    }

    public int getTotalPrice(List<PurchaseItem> purchaseItemList) {
        int totalPrice = 0;
        int purchaseItemCount = purchaseItemList.size();
        for (int i = 0; i < purchaseItemCount; i++) {
            PurchaseItem purchaseItem = purchaseItemList.get(i);
            int price = purchaseItem.getPurchasePrice() * purchaseItem.getPurchaseCount();
            totalPrice += price;
        }
        return totalPrice;
    }
}
