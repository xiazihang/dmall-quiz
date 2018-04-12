package cn.tws.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int count;

    @NotNull
    private int lockedCount;

    @OneToOne(mappedBy = "inventory")  //一旦加了mappedBy就是关系的被维护方
    @JoinColumn(name = "inventoryId")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLockedCount() {
        return lockedCount;
    }

    public void setLockedCount(int lockedCount) {
        this.lockedCount = lockedCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
