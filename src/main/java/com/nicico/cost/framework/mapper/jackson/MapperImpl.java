package com.nicico.cost.framework.mapper.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToJson(@NotNull Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
