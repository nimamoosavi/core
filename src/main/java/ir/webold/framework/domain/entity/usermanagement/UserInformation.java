package ir.webold.framework.domain.entity.usermanagement;

import ir.webold.framework.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Table(name = "US_USERINFORMATION")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Where(clause = "deleted = false")
public class UserInformation extends BaseEntity<Long> {

    @Column(name = "FIRSTNAME", length = 20)
    public String firstName;

    @Column(name = "LASTNAME", length = 20)
    public String lastName;

    @Column(name = "ISSUENUMBER", length = 12)
    public String issueNumber;

    @Column(name = "IDNUMBER", length = 12)
    public String idNumber;

    @Column(name = "TELL", length = 15)
    public String tell;

    @Column(name = "MOBILE", length = 15)
    public String mobile;

    @Column(name = "ADDRESS")
    public String address;

    @Column(name = "POSTALCODE", length = 15)
    public String postalCode;

    @Column(name = "EMAIL", length = 50)
    public String email;

    @OneToOne
    @JoinColumn(name = "USERID", unique = true)
    public User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Office_ID")
    private Office office;

}
