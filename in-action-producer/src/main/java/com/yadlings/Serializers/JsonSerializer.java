package com.yadlings.Serializers;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;
@NoArgsConstructor
public class JsonSerializer<T> implements Serializer<T> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, T t) {
        return new Gson().toJson(t).getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void close() {
        Serializer.super.close();
    }
}
