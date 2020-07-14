package ir.webold.framework.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY_VALUE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted = false")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PropertyValue extends BaseEntity<Long> {

    @Column(name = "FATITTLE", length = 100)
    String farsiTittle;

    @Column(name = "ENTITTLE", length = 100)
    String englishTittle;

    @Column(name = "CODE", length = 10)
    String code;

    @Column(name = "PARENTCODE", length = 20)
    String parentCode;


}
