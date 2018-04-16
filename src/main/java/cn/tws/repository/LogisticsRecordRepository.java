package cn.tws.repository;

import cn.tws.entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord, Long> {
    LogisticsRecord save(LogisticsRecord logisticsRecord);
}
