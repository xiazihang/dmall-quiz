package cn.tws.controller;

import cn.tws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(value = "/orders")
    ResponseEntity createOrder(@RequestBody List<HashMap> orderInfoArray) throws Exception {
        Map result = new HashMap();
        orderInfoArray.stream().map(orderInfo -> {
            String productId = orderInfo.get("productId").toString();
            String purchaseCount = orderInfo.get("purchaseCount").toString();
            createInventory(productId, purchaseCount);
            return productId + purchaseCount;
        }).collect(Collectors.toList());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    public void createInventory(String productId, String purchaseCount) {

    }

    @GetMapping(value = "/orders/1")
    ResponseEntity getOrderInfo() throws Exception {

    }
}
