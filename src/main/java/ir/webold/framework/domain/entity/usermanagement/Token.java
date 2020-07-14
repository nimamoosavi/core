package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "US_TOKEN")
@Setter
@Getter
@Where(clause = "deleted = false")
public class Token extends BaseEntity<Long> {


    @Column(name = "TOKEN")
    private String accessToken;
    @Column(name = "STARTTIME")
    private Timestamp startTime;
    @Column(name = "ENDTIME")
    private Timestamp expireTime;
    @Column(name = "USERID")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "REFTOKENID")
    private RefreshToken refreshToken;

    @PrePersist
    public void prePersist() {
        startTime = new Timestamp(System.currentTimeMillis());
    }
}
