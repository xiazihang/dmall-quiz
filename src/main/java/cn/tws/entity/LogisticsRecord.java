package cn.tws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class LogisticsRecord {
    @Id
    @GeneratedValue

    private Long id;
    private Enum logisticsStatus;
    private String deliveryMan;
    private Date outboundTime;
    private Date signedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Enum logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Date getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }

    public Date getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }
}
