package com.yadlings.Serializers;

import com.google.gson.Gson;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T>{
    private Class<T> DeserClass;
    Gson gson;
    JsonDeserializer(Class<T> tClass){
        this.gson = new Gson();
        this.DeserClass = tClass;
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(bytes.toString(),DeserClass);
    }
    @Override
    public void close() {
        Deserializer.super.close();
    }
}
