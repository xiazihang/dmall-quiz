 INSERT INTO inventory (id,count,lockedCount) VALUES ( 1, 100, 99 );
 INSERT INTO inventory (id,count,lockedCount) VALUES ( 2, 100, 98 );
 INSERT INTO inventory (id,count,lockedCount) VALUES ( 3, 100, 97 );

 INSERT INTO product (id,name,description,price,inventoryId) VALUES ( 1, '晨光铅笔','2B铅笔',5, 1 );
 INSERT INTO product (id,name,description,price,inventoryId) VALUES ( 2, '方便面','康师傅',5, 2 );
 INSERT INTO product (id,name,description,price,inventoryId) VALUES ( 3, '农夫山泉','矿泉水',3, 3 );

 INSERT INTO logisticsRecord (id,logisticsStatus,outboundTime,signedTime,deliveryMan) VALUES ( 1, 'readyToShip',NULL ,NULL, '李师傅' );
 INSERT INTO logisticsRecord (id,logisticsStatus,outboundTime,signedTime,deliveryMan) VALUES ( 2, 'shipping','2018-04-11 09:36:21',NULL ,'李师傅');
 INSERT INTO logisticsRecord (id,logisticsStatus,outboundTime,signedTime,deliveryMan) VALUES ( 3, 'signed','2018-04-11 09:36:21','2018-04-12 09:36:21','李师傅');

 INSERT INTO orders (id,userId,totalPrice,status,createTiem,paidTime,withdrawnTime,finishTime) VALUES ( 1, 1, 13, 'unPaid','2018-04-11 09:36:21',NULL ,NULL ,NULL );
 INSERT INTO orders (id,userId,totalPrice,status,createTiem,paidTime,withdrawnTime,finishTime) VALUES ( 2, 1, 13, 'paid','2018-04-11 09:36:21','2018-04-11 09:36:21' ,NULL ,NULL );
 INSERT INTO orders (id,userId,totalPrice,status,createTiem,paidTime,withdrawnTime,finishTime) VALUES ( 3, 1, 13, 'withdrawn','2018-04-11 09:36:21',NULL ,'2018-04-11 09:36:21' ,NULL  );
 INSERT INTO orders (id,userId,totalPrice,status,createTiem,paidTime,withdrawnTime,finishTime) VALUES ( 4, 1, 13, 'finished','2018-04-11 09:36:21','2018-04-11 09:36:21' ,NULL ,'2018-04-11 09:36:21'  );

 INSERT INTO purchaseItem (id,productName,productDescription,purchaseCount,purchasePrice,orderId,productId) VALUES ( 1, '晨光铅笔','2B铅笔', 1, 5, 1,1);
 INSERT INTO purchaseItem (id,productName,productDescription,purchaseCount,purchasePrice,orderId,productId) VALUES ( 2, '方便面','康师傅', 1, 5, 2,2);
 INSERT INTO purchaseItem (id,productName,productDescription,purchaseCount,purchasePrice,orderId,productId) VALUES ( 3, '农夫山泉','矿泉水', 1, 3, 3,3);