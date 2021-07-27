package com.yadlings.topologytest.Serdes;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {
    private final Class<T> desClass;

    public JsonDeserializer(Class<T> tClass){
        this.desClass = tClass;
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return new Gson().fromJson(new String(bytes),desClass);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
