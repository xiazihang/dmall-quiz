package cn.tws.entity;

import javax.persistence.*;
import cn.tws.utils.OrderStatus;
import java.sql.Timestamp;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Timestamp createTime;
    private Timestamp finishTime;
    private Timestamp paidTime;
    private Timestamp withdrawnTime;
}
