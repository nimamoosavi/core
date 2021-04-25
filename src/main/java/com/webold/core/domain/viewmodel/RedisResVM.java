package com.webold.core.domain.viewmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisResVM {
    private Object object;
    private Long expireTime;
}
