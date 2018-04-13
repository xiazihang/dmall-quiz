package cn.tws.controller;

import cn.tws.entity.LogisticsRecord;
import cn.tws.entity.Orders;
import cn.tws.repository.LogisticsRecordRepository;
import cn.tws.repository.OrderRepository;
import cn.tws.utils.LogisticsStatus;
import cn.tws.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/logisticsRecord")
public class LogisticsRecordController {

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity getRecordRepository(@PathVariable Long id) throws Exception {
        LogisticsRecord logisticsRecord = logisticsRecordRepository.findOne(id);
        return new ResponseEntity<>(logisticsRecord, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity shipping(@PathVariable Long id) throws Exception {
        LogisticsRecord oldLogisticsRecord = logisticsRecordRepository.findOne(id);

        if(oldLogisticsRecord.getLogisticsStatus() == LogisticsStatus.shipping){
            return new ResponseEntity<>("该订单已发货", HttpStatus.OK);
        }
        if(oldLogisticsRecord.getLogisticsStatus() == LogisticsStatus.signed){
            return new ResponseEntity<>("该订单已签收", HttpStatus.OK);
        }
        oldLogisticsRecord.setOutboundTime(new Timestamp(System.currentTimeMillis()));
        oldLogisticsRecord.setLogisticsStatus(LogisticsStatus.shipping);
        logisticsRecordRepository.save(oldLogisticsRecord);
        return new ResponseEntity<>("发货成功", HttpStatus.OK);
    }

    @PutMapping(value = "/sign/{id}")
    public ResponseEntity sign(@PathVariable Long id) throws Exception {
        LogisticsRecord oldLogisticsRecord = logisticsRecordRepository.findOne(id);

        oldLogisticsRecord.setSignedTime(new Timestamp(System.currentTimeMillis()));
        oldLogisticsRecord.setLogisticsStatus(LogisticsStatus.signed);
        logisticsRecordRepository.save(oldLogisticsRecord);

        Orders oldOrder = orderRepository.findByLogisticsRecordId(id);
        oldOrder.setStatus(OrderStatus.finished);
        oldOrder.setFinishTime(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(oldOrder);

        return new ResponseEntity<>("签收成功", HttpStatus.OK);
    }
}
