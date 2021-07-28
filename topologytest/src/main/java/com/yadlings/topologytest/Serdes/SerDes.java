package com.yadlings.topologytest.Serdes;

import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Domain.UserRoleCount;
import org.apache.kafka.common.serialization.Serde;

public interface SerDes{
    static Serde<User> UserSerde(){
        return new JsonSerdes<User>(new JsonSerializer<>(),new JsonDeserializer<>(User.class));
    }

    static Serde<UserRoleCount> UseCountSerde() {
        return new JsonSerdes<UserRoleCount>(new JsonSerializer<>(),new JsonDeserializer<>(UserRoleCount.class));
    }
}

