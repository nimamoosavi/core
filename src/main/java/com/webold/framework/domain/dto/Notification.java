package com.webold.framework.domain.dto;


import com.webold.framework.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Notification {
    private Status status;
    private Object notify;
}
