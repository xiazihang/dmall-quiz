CREATE TABLE orders(
  id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userId int(10),
  totalPrice int(10),
  createTiem TIMESTAMP default current_timestamp,
  paidTime TIMESTAMP default current_timestamp,
  withdrawnTime TIMESTAMP default current_timestamp,
  finishTime TIMESTAMP default current_timestamp,
  status ENUM('unPaid','paid','withdrawn','finished'),
  logisticsRecordId int(10),
  FOREIGN KEY (logisticsRecordId) REFERENCES logisticsRecord(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
