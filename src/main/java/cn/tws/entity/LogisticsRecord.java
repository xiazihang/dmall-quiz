package cn.tws.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "logisticsRecord")
public class LogisticsRecord {
    @Id
    @GeneratedValue

    private Long id;
    private String deliveryMan;
    private Date outboundTime;
    private Date signedTime;

    public static enum LogisticsStatus {
        readyToShip,
        shipping,
        signed
    }
    @Enumerated(EnumType.STRING)
    private LogisticsStatus logisticsStatus;

    public void setLogisticsStatus(LogisticsStatus logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum getLogisticsStatus() {
        return logisticsStatus;
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
