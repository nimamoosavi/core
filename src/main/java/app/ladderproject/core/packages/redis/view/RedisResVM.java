package app.ladderproject.core.packages.redis.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisResVM {
    private Object object;
    private Long expireTime;
}
