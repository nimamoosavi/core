package com.webold.core.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity<I extends Serializable> implements Serializable {
    @Transient
    private static final long serialVersionUID = 6488252903669024162L;

    public BaseEntity(I id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private I id;

    private boolean deleted;

    @Column(name = "CREATED_DATE")
    private Timestamp createDate;

    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;

    @Column(name = "DESCRIPTION")
    private String description;


    @PrePersist
    public void createdDate() {
        createDate = new Timestamp(System.currentTimeMillis());
        deleted = false;
    }

    @PreUpdate
    public void updateDate() {
        updateDate = new Timestamp(System.currentTimeMillis());
    }

}
