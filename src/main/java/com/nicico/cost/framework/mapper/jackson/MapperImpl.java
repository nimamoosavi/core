package com.nicico.cost.framework.mapper.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

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
    public <G> G jsonToObject(String json, TypeReference<G> reference) {
        try {
            return objectMapper.readValue(json, reference);
        } catch (IOException e) {
            return null;
        }
    }


}
