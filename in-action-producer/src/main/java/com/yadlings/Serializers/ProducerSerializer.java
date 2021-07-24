package com.yadlings.Serializers;

import com.google.gson.Gson;
import com.yadlings.Domain.User;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ProducerSerializer implements Serializer<User> {
    private Gson gSon = new Gson();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, User t) {
        return gSon.toJson(t).getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void close() {

    }
}
