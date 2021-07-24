package com.yadlings.Serializers;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;
@Log4j2
public class Des<T> implements Deserializer<T> {
    Map<String, ?> conf;
    boolean key;
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.info("CONFIG {}",configs);
        key = isKey;
        conf = !isKey ? configs:null;
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        log.info("Deserializing {} key {}",conf,key);
        return null;
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        Type type;

        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
