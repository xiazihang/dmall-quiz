CREATE TABLE logisticsRecord(
  id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  logisticsStatus ENUM('readyToShip','shipping','signed'),
  outboundTime TIMESTAMP default current_timestamp,
  signedTime TIMESTAMP default current_timestamp,
  deliveryMan int(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
