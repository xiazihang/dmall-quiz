package cn.tws.repository;

import cn.tws.entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord, Long> {
}
