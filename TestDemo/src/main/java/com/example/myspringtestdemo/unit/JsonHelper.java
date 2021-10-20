package com.example.myspringtestdemo.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static <T> String serializable(T object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T deserialization(String json, Class<T> valueType) {
        return objectMapper.readValue(json, valueType);
    }
}
