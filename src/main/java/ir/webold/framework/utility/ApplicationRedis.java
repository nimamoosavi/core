package ir.webold.framework.utility;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Component
public class ApplicationRedis {

    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ApplicationRedis(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Value("${spring.application.name}")
    private String microserviceName;


    public BaseDTO<Boolean> setIn(String key, Object o) {
        redisTemplate.opsForValue().set(generateKey(key), o);
        return successCustomResponse(true);
    }


    public BaseDTO<Boolean> setIn(String key, Object o, Long expireTime) {
        redisTemplate.opsForValue().set(generateKey(key), o, expireTime, TimeUnit.MILLISECONDS);
        return successCustomResponse(true);
    }

    @Async("treadPoolAsync")
    public BaseDTO<Boolean> setAsyncIn(String key, Object o) {
        redisTemplate.opsForValue().set(generateKey(key), o);
        return successCustomResponse(true);
    }

    @Async("treadPoolAsync")
    public BaseDTO<Boolean> setAsyncIn(String key, Object o, Long expireTime) {
        redisTemplate.opsForValue().set(generateKey(key), o, expireTime, TimeUnit.MILLISECONDS);
        return successCustomResponse(true);
    }


    public BaseDTO<Object> getFrom(String key, Boolean expireAfterFetch) {
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        if (Boolean.TRUE.equals(expireAfterFetch)) {
            redisTemplate.delete(generateKey(key));
        }
        return successCustomResponse(o);
    }

    public String generateKey(String key) {
        return microserviceName.concat("-").concat(key);
    }

}
