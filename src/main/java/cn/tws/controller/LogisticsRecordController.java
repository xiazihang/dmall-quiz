package cn.tws.controller;

import cn.tws.entity.LogisticsRecord;
import cn.tws.repository.LogisticsRecordRepository;
import cn.tws.utils.LogisticsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logisticsRecord")
public class LogisticsRecordController {

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @PostMapping("")
    public ResponseEntity createRecordRepository(@RequestBody LogisticsRecord logisticsRecord) throws Exception {
        logisticsRecordRepository.save(logisticsRecord);
        LogisticsRecord logisticsRecords = logisticsRecordRepository.findOne(logisticsRecord.getId());
        return new ResponseEntity<>(logisticsRecords,HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getRecordRepository(@PathVariable Long id) throws Exception {
        LogisticsRecord logisticsRecord = logisticsRecordRepository.findOne(id);
        return new ResponseEntity<>(logisticsRecord, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity shipping(@PathVariable Long id, @RequestBody LogisticsRecord logisticsRecord) throws Exception {
        LogisticsRecord oldLogisticsRecord = logisticsRecordRepository.findOne(id);
        oldLogisticsRecord.setOutboundTime(logisticsRecord.getOutboundTime());
        oldLogisticsRecord.setSignedTime(logisticsRecord.getSignedTime());
        oldLogisticsRecord.setLogisticsStatus((LogisticsStatus) logisticsRecord.getLogisticsStatus());
        logisticsRecordRepository.save(oldLogisticsRecord);
        return new ResponseEntity<>(logisticsRecordRepository.findOne(id), HttpStatus.OK);
    }
}
