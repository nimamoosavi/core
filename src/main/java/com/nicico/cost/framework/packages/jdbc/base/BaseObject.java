package com.nicico.cost.framework.packages.jdbc.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseObject<I extends Serializable> implements Serializable {
    @Transient
    private static final long serialVersionUID = 64882529036694162L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private I id;


}
