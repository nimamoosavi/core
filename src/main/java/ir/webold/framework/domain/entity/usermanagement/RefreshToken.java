package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "US_REFRESHTOKEN")
@Setter
@Getter
@Where(clause = "deleted = false")
public class RefreshToken extends BaseEntity<Long> {

    @Column(name = "REFRESHTOKEN")
    private String refToken;
    @Column(name = "STARTTIME")
    private Timestamp startTime;
    @Column(name = "ENDTIME")
    private Timestamp expireTime;


    @PrePersist
    public void prePersist() {
        startTime = new Timestamp(System.currentTimeMillis());
    }
}
