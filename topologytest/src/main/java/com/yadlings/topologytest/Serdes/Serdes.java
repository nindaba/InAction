package com.yadlings.topologytest.Serdes;

import com.yadlings.topologytest.Domain.User;
import org.apache.kafka.common.serialization.Serde;

public interface Serdes{
    static Serde<User> UserSerde(){
        return new JsonSerdes<User>(new JsonSerializer<>(),new JsonDeserializer<>(User.class));
    }
}
