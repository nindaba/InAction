package com.yadlings.Serializers;

import com.yadlings.Domain.User;
import com.yadlings.Domain.UserCount;

public interface DomainSerde {
    static JsonSerde<User> userSerde(){
        return new JsonSerde(new JsonSerializer<User>(), new JsonDeserializer<User>(User.class));
    }
    static JsonSerde<User> userCountSerde(){
        return new JsonSerde(new JsonSerializer<UserCount>(), new JsonDeserializer<UserCount>(UserCount.class));
    }
}
