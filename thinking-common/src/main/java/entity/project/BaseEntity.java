package entity.project;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "create_time", nullable = false, updatable = false)
    LocalDateTime createTime;

    @Column(name = "creator", nullable = false, updatable = false)
    String creator;

    @Column(name = "modify_time", nullable = false)
    LocalDateTime modifyTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        modifyTime = now;
        createTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        modifyTime = LocalDateTime.now();
    }
}
