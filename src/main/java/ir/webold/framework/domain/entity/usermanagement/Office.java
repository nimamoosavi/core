package ir.webold.framework.domain.entity.usermanagement;


import ir.webold.framework.domain.entity.BaseEntity;
import ir.webold.framework.domain.entity.PropertyValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Table(name = "US_OFFICE")
@Setter
@Getter
@Where(clause = "deleted = false")
public class Office extends BaseEntity<Long> {

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTALCODE", length = 15)
    private String postalCode;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "WEB", length = 50)
    private String web;
    @Column(name = "TELNUMBER")
    private String telNumber;
    @Column(name = "FAX")
    private String faxNumber;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "OFFICETYPE_FK", nullable = true)
    public PropertyValue officeType;

}
