package com.yadlings.Serializers;

import com.yadlings.Domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
@AllArgsConstructor
public class JsonSerde<T> implements Serde<T> {
    private JsonSerializer jsonSerializer;
    private JsonDeserializer jsonDeserializer;
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serde.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serde.super.close();
    }

    @Override
    public Serializer<T> serializer() {
        return jsonSerializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return jsonDeserializer;
    }
}

