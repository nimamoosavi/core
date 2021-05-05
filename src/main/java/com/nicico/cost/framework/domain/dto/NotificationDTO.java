package com.nicico.cost.framework.domain.dto;


import com.nicico.cost.framework.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class NotificationDTO {
    private ResultStatus status;
    private Object notify;
}
