package ir.webold.framework.utility;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.viewmodel.RedisResVM;
import ir.webold.framework.enums.exception.ExceptionEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static ir.webold.framework.service.GeneralService.successCustomResponse;


@Component
public class ApplicationRedis {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ModelMapper modelMapper;
    private final ApplicationException applicationException;

    @Autowired
    public ApplicationRedis(RedisTemplate<String, Object> redisTemplate,ModelMapper modelMapper,ApplicationException applicationException) {
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

    @Async("treadPoolAsync")
    public void setAsyncIn(String key, Object o) {
        redisTemplate.opsForValue().set(generateKey(key), o);
    }

    @Async("treadPoolAsync")
    public void setAsyncIn(String key, Object o, Long expireTime) {
        redisTemplate.opsForValue().set(generateKey(key), o, expireTime, TimeUnit.MILLISECONDS);
    }


    public <T> BaseDTO<T> fetch(String key, Boolean expireAfterFetch,Class<T> tClass) {
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        if (Boolean.TRUE.equals(expireAfterFetch))
            redisTemplate.delete(generateKey(key));
        if (o!=null)
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
        BaseDTO<Long> baseDTO = getExpire(key,TimeUnit.MILLISECONDS).orElseThrow(
                applicationException.createApplicationException(ExceptionEnum.NOTFOUND)
        );
        Object o = redisTemplate.opsForValue().get(generateKey(key));
        if (Boolean.TRUE.equals(expireAfterFetch))
            redisTemplate.delete(generateKey(key));
        RedisResVM redisResVM = RedisResVM.builder().object(o).expireTime(baseDTO.getData()).build();
        return successCustomResponse(redisResVM);
    }

    public BaseDTO<Long> getExpire(String key,TimeUnit timeUnit){
        Long expire = redisTemplate.getExpire(key,timeUnit);
        return successCustomResponse(expire);
    }

    private String generateKey(String key) {
        return microserviceName.concat("-").concat(key);
    }

}
