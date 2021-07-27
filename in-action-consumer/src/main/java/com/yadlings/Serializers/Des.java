package com.yadlings.Serializers;

import com.google.gson.Gson;
import com.yadlings.Domain.User;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;
@Log4j2
public class Des<T> implements Deserializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.info("CONFIG {}",configs);
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        return new Gson().fromJson(new String(bytes),User.class);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
