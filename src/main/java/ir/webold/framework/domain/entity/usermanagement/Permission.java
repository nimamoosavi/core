package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "US_PERMISSION")
@Setter
@Getter
@Where(clause = "deleted = false")
public class Permission extends BaseEntity<Long> {

    @Column(name = "resource", unique = true, length = 50)
    String resource;
    @Column(name = "url", length = 500)
    String url;
    @Column(name = "httpMethod")
    String httpMethod;
    @Column(name = "token")
    Boolean token;

}
