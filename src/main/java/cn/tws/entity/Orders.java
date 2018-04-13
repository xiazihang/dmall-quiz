package cn.tws.entity;

import javax.persistence.*;

import cn.tws.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue
    private Long id;
    private int totalPrice;
    private int userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Timestamp createTime;
    private Timestamp finishTime;
    private Timestamp paidTime;
    private Timestamp withdrawnTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<PurchaseItem> PurchaseItemList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true, name = "logisticsRecordId")
    private LogisticsRecord logisticsRecord;

    public Orders() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
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

    public List<PurchaseItem> getPurchaseItemList() {
        return PurchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
        PurchaseItemList = purchaseItemList;
    }

    public LogisticsRecord getLogisticsRecord() {
        return logisticsRecord;
    }

    public void setLogisticsRecord(LogisticsRecord logisticsRecord) {
        this.logisticsRecord = logisticsRecord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
