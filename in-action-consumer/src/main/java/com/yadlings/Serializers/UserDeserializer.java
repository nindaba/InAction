package com.yadlings.Serializers;

import com.google.gson.Gson;
import com.yadlings.Domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

import javax.ws.rs.core.GenericType;
import java.lang.reflect.Type;
import java.util.Map;
//@AllArgsConstructor

@NoArgsConstructor
@Log4j2
public class UserDeserializer<T> implements Deserializer<T> {
    private static final Type T = null;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return bytes!= null ? new Gson().fromJson(new String(bytes),T):null;
    }
    @Override
    public void close() {
        Deserializer.super.close();
    }

}
