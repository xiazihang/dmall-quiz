package cn.tws.controller;

import cn.tws.entity.Inventory;
import cn.tws.entity.LogisticsRecord;
import cn.tws.entity.Order;
import cn.tws.entity.PurchaseItem;
import cn.tws.repository.InventoryRepository;
import cn.tws.repository.LogisticsRecordRepository;
import cn.tws.repository.OrderRepository;
import cn.tws.utils.LogisticsStatus;
import cn.tws.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logisticsRecords")
public class LogisticsRecordController {

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public LogisticsRecordController(LogisticsRecordRepository logisticsRecordRepository) {
        this.logisticsRecordRepository = logisticsRecordRepository;
    }

    public LogisticsRecordController() {
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity getRecordRepository(@PathVariable(name = "orderId") Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderOptional.get();
        if (order.getStatus() == OrderStatus.unPaid || order.getStatus() == OrderStatus.withdrawn) {
            return ResponseEntity.notFound().build();
        }

        LogisticsRecord logisticsRecord = order.getLogisticsRecord();
        return ResponseEntity.ok(logisticsRecord);
    }

    @PutMapping(value = "/{id}/orders/{orderId}")
    public ResponseEntity updateLogisticsRecordStatus(@PathVariable("id") Long logisticsRecordId, @PathVariable("orderId") Long orderId, @RequestParam LogisticsStatus logisticsStatus) throws Exception {
        Optional<LogisticsRecord> logisticsRecordOptional = logisticsRecordRepository.findById(logisticsRecordId);
        if (!logisticsRecordOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        LogisticsRecord logisticsRecord = logisticsRecordOptional.get();

        if (logisticsStatus == LogisticsStatus.shipping) {
            return shipLogistics(logisticsRecord);
        }

        if (logisticsStatus == LogisticsStatus.signed) {
            return signLogisticsRecord(logisticsRecord,orderId);
        }

        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity signLogisticsRecord(LogisticsRecord logisticsRecord,Long orderId) {
        if (logisticsRecord.getLogisticsStatus() != LogisticsStatus.shipping) {
            return ResponseEntity.badRequest().build();
        }

        logisticsRecord.setLogisticsStatus(LogisticsStatus.signed);
        logisticsRecord.setSignedTime(new Timestamp(System.currentTimeMillis()));
        logisticsRecordRepository.save(logisticsRecord);
        new OrderController(orderRepository).finishOrder(orderRepository.findById(orderId).get());

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity shipLogistics(LogisticsRecord logisticsRecord) {
        if (logisticsRecord.getLogisticsStatus() != LogisticsStatus.readyToShip) {
            return ResponseEntity.badRequest().build();
        }

        logisticsRecord.setLogisticsStatus(LogisticsStatus.shipping);
        logisticsRecord.setOutboundTime(new Timestamp(System.currentTimeMillis()));
        logisticsRecordRepository.save(logisticsRecord);

        return ResponseEntity.noContent().build();
    }


//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity shipping(@PathVariable Long id) throws Exception {
//        LogisticsRecord oldLogisticsRecord = logisticsRecordRepository.findOne(id);
//        if(oldLogisticsRecord == null){
//            return new ResponseEntity<>("没有该物流订单", HttpStatus.OK);
//        }
//        if (oldLogisticsRecord.getLogisticsStatus() == LogisticsStatus.shipping) {
//            return new ResponseEntity<>("该订单已发货", HttpStatus.OK);
//        }
//        if (oldLogisticsRecord.getLogisticsStatus() == LogisticsStatus.signed) {
//            return new ResponseEntity<>("该订单已签收", HttpStatus.OK);
//        }
//        oldLogisticsRecord.setOutboundTime(new Timestamp(System.currentTimeMillis()));
//        oldLogisticsRecord.setLogisticsStatus(LogisticsStatus.shipping);
//        logisticsRecordRepository.save(oldLogisticsRecord);
//        return new ResponseEntity<>("发货成功", HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/sign/{id}")
//    public ResponseEntity sign(@PathVariable Long id) throws Exception {
//        LogisticsRecord oldLogisticsRecord = logisticsRecordRepository.findOne(id);
//        if(oldLogisticsRecord == null){
//            return new ResponseEntity<>("没有该物流订单", HttpStatus.OK);
//        }
//        if(oldLogisticsRecord.getLogisticsStatus() == LogisticsStatus.signed){
//            return new ResponseEntity<>("该订单已签收", HttpStatus.OK);
//        }
//
//        oldLogisticsRecord.setSignedTime(new Timestamp(System.currentTimeMillis()));
//        oldLogisticsRecord.setLogisticsStatus(LogisticsStatus.signed);
//        logisticsRecordRepository.save(oldLogisticsRecord);
//
//        Order oldOrder = orderRepository.findByLogisticsRecordId(id);
//        if (oldOrder == null) {
//            return new ResponseEntity<>(" 该物流单没有相应的订单", HttpStatus.OK);
//        }
//
//        List<PurchaseItem> purchaseItems = oldOrder.getPurchaseItemList();
//        for (PurchaseItem purchaseItem : purchaseItems) {
//            Inventory inventory = inventoryRepository.findByProductId(purchaseItem.getProductId());
//            inventory.setCount(inventory.getCount() - purchaseItem.getPurchaseCount());
//            inventoryRepository.save(inventory);
//        }
//
//        oldOrder.setStatus(OrderStatus.finished);
//        oldOrder.setFinishTime(new Timestamp(System.currentTimeMillis()));
//        orderRepository.save(oldOrder);
//
//        return new ResponseEntity<>("签收成功", HttpStatus.OK);
//    }

    public LogisticsRecord createLogisticsRecord() {
        LogisticsRecord logisticsRecord = new LogisticsRecord();
        logisticsRecord.setLogisticsStatus(LogisticsStatus.readyToShip);
        logisticsRecord.setDeliveryMan("李师傅");

        return logisticsRecordRepository.save(logisticsRecord);
    }
}
