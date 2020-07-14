package ir.webold.framework.domain.entity.usermanagement;


import ir.webold.framework.domain.entity.BaseEntity;
import ir.webold.framework.domain.entity.PropertyValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "US_USERS")
@Setter
@Getter
@Where(clause = "deleted = false")
public class User extends BaseEntity<Long> {

    @Column(name = "USERNAME", length = 20, nullable = true, unique = true)
    String username;
    @Column(name = "PASSWORD", nullable = true)
    String password;
    @Column(name = "ACCOUNTEXPIRED", nullable = true)
    Boolean accountExpired;
    @Column(name = "ACCOUNTLOCKED", nullable = true)
    Boolean accountLocked;
    @Column(name = "ACCOUNTENABLE", nullable = true)
    Boolean accountEnable;
    @Column(name = "FAILEDLOGIN")
    Integer failedLogin;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "US_USERROLE",
                    joinColumns = {@JoinColumn(name = "USER_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
            )
    private List<Role> roles;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USERTYPE_ID")
    private PropertyValue userType;


    @PrePersist
    public void prePersist() {
        accountExpired = false;
        accountLocked = false;
        accountEnable = true;
        failedLogin = 0;
    }

}
