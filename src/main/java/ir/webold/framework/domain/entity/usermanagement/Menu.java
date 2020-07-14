package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "US_MENU")
@Setter
@Getter
@Where(clause = "deleted = false")
public class Menu extends BaseEntity<Long> {

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String enTitle;

    @Column(length = 150)
    private String path;

    @Column(name = "PARENTID")
    private Long parent;

    @Column(name = "MENUKEY")
    private String menuKey;

    @PrePersist
    public void setMenuKey() {
        menuKey = UUID.randomUUID().toString();
    }

}
