package com.webold.core.utility;

import com.webold.core.domain.viewmodel.RedisResVM;
import com.webold.core.enums.exception.ExceptionEnum;
import com.webold.core.domain.dto.BaseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.webold.core.service.GeneralService.successCustomResponse;


@Component
public class ApplicationRedis {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ModelMapper modelMapper;
    private final ApplicationException applicationException;

    @Autowired
    public ApplicationRedis(RedisTemplate<String, Object> redisTemplate, ModelMapper modelMapper, ApplicationException applicationException) {
        this.redisTemplate = redisTemplate;
        this.modelMapper = modelMapper;
        this.applicationException = applicationException;
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


    public BaseDTO<Object> updateIfPresent(String key, Object o) {
        Object obj = redisTemplate.opsForValue().getAndSet(generateKey(key), o);
        return successCustomResponse(obj);
    }


    @Async("treadPoolAsync")
    public void setAsyncIn(String key, Object o) {
        redisTemplate.opsForValue().set(generateKey(key), o);
    }

    @Async("treadPoolAsync")
    public void setAsyncIn(String key, Object o, Long expireTime) {
        redisTemplate.opsForValue().set(generateKey(key), o, expireTime, TimeUnit.MILLISECONDS);
    }

    @Async("treadPoolAsync")
    public void setAsyncInIfPresent(String key, Object o) {
        redisTemplate.opsForValue().setIfAbsent(generateKey(key), o);
    }


    public <T> BaseDTO<T> fetch(String key, Boolean expireAfterFetch, Class<T> tClass) {
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        if (Boolean.TRUE.equals(expireAfterFetch))
            redisTemplate.delete(generateKey(key));
        if (o != null)
            return successCustomResponse(modelMapper.map(o, tClass));
        return successCustomResponse(null);
    }

    public BaseDTO<Object> fetch(String key, Boolean expireAfterFetch) {
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        if (Boolean.TRUE.equals(expireAfterFetch))
            redisTemplate.delete(generateKey(key));
        return successCustomResponse(o);
    }

    public BaseDTO<RedisResVM> fetchComplete(String key, Boolean expireAfterFetch) {
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        BaseDTO<Long> baseDTO = getExpireTime(key, TimeUnit.MILLISECONDS).orElseThrow(
                applicationException.createApplicationException(ExceptionEnum.NOTFOUND)
        );
        if (Boolean.TRUE.equals(expireAfterFetch))
            redisTemplate.delete(generateKey(key));
        RedisResVM redisResVM = RedisResVM.builder().object(o).expireTime(baseDTO.getData()).build();
        return successCustomResponse(redisResVM);
    }

    public BaseDTO<Long> getExpireTime(String key, TimeUnit timeUnit) {
        Long expire = redisTemplate.getExpire(generateKey(key), timeUnit);
        return successCustomResponse(expire).orElseThrow(
                applicationException.createApplicationException(ExceptionEnum.NOTFOUND)
        );
    }

    public BaseDTO<Boolean> delete(String key) {
        Boolean delete = redisTemplate.delete(generateKey(key));
        return successCustomResponse(delete);
    }

    private String generateKey(String key) {
        return microserviceName.concat("-").concat(key);
    }

}
