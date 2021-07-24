package com.yadlings.Serializers;

import com.google.gson.Gson;
import com.yadlings.Domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
@NoArgsConstructor
@Log4j2
public class UserDeserializer implements Deserializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        return bytes!= null ? new Gson().fromJson(bytes.toString(),User):null;
    }
    @Override
    public void close() {
        Deserializer.super.close();
    }

}
