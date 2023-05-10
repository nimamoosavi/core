package app.ladderproject.core.mapper.jackson;

import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

import static app.ladderproject.core.enums.exception.Exceptions.INTERNAL_SERVER;


@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {

    private final ObjectMapper objectMapper;
    private final ApplicationException<ServiceException> applicationException;

    @Override
    public String convertToJson(@NotNull Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public <G> G jsonToObject(String json, Class<G> aClass) {
        try {
            return objectMapper.readValue(json, new TypeReference<G>() {
            });
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public <G> G mapToObject(Map<String, Object> obj, Class<G> aClass) {
        try {
            return objectMapper.convertValue(obj, new TypeReference<G>() {});
        } catch (Exception e) {
            throw  applicationException.createApplicationException(INTERNAL_SERVER, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public <G> G mapStrToObject(Map<String, String> obj, Class<G> aClass) {
        try {
            return objectMapper.convertValue(obj, aClass);
        } catch (Exception e) {
            throw  applicationException.createApplicationException(INTERNAL_SERVER, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public <G> G jsonToObject(String json, TypeReference<G> reference) {
        try {
            return objectMapper.readValue(json, reference);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public <G, T> JavaType castToJavaType(Class<G> g, Class<T> t) {
        return objectMapper.getTypeFactory().constructParametricType(g,t);
    }

    @Override
    public <G> G jsonToObject(String json, JavaType javaType) {
        try {
            return objectMapper.readValue(json,javaType);
        }catch (IOException e) {
            return null;
        }
    }
}
