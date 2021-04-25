package com.webold.core.domain.viewmodel;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseResVM<I extends Serializable> {
    I id;
    String description;
    Timestamp createDate;
    Timestamp updateDate;
}
