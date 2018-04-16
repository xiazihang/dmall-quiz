package cn.tws.repository;

import cn.tws.entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord, Long> {
    Optional<LogisticsRecord> findById(Long id);
}
