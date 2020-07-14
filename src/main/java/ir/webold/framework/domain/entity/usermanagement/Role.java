package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "US_ROLES")
@Getter
@Setter
@Where(clause = "deleted = false")
public class Role extends BaseEntity<Long> {
    @Column(name = "TITTLE", length = 50)
    String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "US_ROLEPERMISSION",
                    joinColumns = {@JoinColumn(name = "ROLE_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID")}
            )
    private List<Permission> permissions;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "US_ROLEMENU",
                    joinColumns = {@JoinColumn(name = "ROLE_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "MENU_ID")}
            )
    private List<Menu> menus;

}