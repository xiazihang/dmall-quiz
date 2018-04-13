package cn.tws.entity;

import javax.persistence.*;
import cn.tws.utils.OrderStatus;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Timestamp createTime;
    private Timestamp finishTime;
    private Timestamp paidTime;
    private Timestamp withdrawnTime;

    public Orders() {
    }

    public Orders(Integer totalPrice, OrderStatus status, Timestamp createTime, Timestamp finishTime, Timestamp paidTime, Timestamp withdrawnTime) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.paidTime = paidTime;
        this.withdrawnTime = withdrawnTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Timestamp getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Timestamp paidTime) {
        this.paidTime = paidTime;
    }

    public Timestamp getWithdrawnTime() {
        return withdrawnTime;
    }

    public void setWithdrawnTime(Timestamp withdrawnTime) {
        this.withdrawnTime = withdrawnTime;
    }
}
