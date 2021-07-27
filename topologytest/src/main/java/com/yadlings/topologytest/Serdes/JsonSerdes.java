package com.yadlings.topologytest.Serdes;

import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
@AllArgsConstructor
public class JsonSerdes<T> implements Serde<T> {
    private JsonSerializer<T> jsonSerializer;
    private JsonDeserializer<T> jsonDeserializer;
    @Override
    public Serializer<T> serializer() {
        return jsonSerializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return jsonDeserializer;
    }
}
