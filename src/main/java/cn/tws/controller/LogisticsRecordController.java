package cn.tws.controller;

import cn.tws.entity.LogisticsRecord;
import cn.tws.repository.LogisticsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class LogisticsRecordController {

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @PostMapping(value = "/logisticsRecord")
    public ResponseEntity createRecordRepository(@RequestBody LogisticsRecord logisticsRecord) throws Exception{
        logisticsRecordRepository.save(logisticsRecord);
        Optional<LogisticsRecord> logisticsRecords = logisticsRecordRepository.findById(logisticsRecord.getId());
        return new ResponseEntity<>(logisticsRecords,HttpStatus.CREATED);
    }
}
