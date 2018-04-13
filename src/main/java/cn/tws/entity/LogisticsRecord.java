package cn.tws.entity;

import cn.tws.utils.LogisticsStatus;

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
    private String outboundTime;
    private String signedTime;


    @Enumerated(EnumType.STRING)
    private LogisticsStatus logisticsStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(LogisticsStatus logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }
}
