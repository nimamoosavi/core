package com.webold.core.domain.dto;

import com.webold.core.enums.ResultStatus;
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
