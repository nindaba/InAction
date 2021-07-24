package com.yadlings.Serializers;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonSerializer<T> implements Serializer<T> {
    private Gson gSon;
    JsonSerializer(){
        this.gSon = new Gson();
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, T t) {
        return gSon.toJson(t).getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void close() {
        Serializer.super.close();
    }
}
