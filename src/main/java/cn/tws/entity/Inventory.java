package cn.tws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventory {
    @Id
    @GeneratedValue

    private Long id;
    private Long count;
    private Long lockedCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLockedCount() {
        return lockedCount;
    }

    public void setLockedCount(Long lockedCount) {
        this.lockedCount = lockedCount;
    }
}
