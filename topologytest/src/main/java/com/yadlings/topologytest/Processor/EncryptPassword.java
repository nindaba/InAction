package com.yadlings.topologytest.Processor;

import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Utils.PasswordEncryptor;
import lombok.NoArgsConstructor;
import org.apache.kafka.streams.kstream.ValueMapper;
@NoArgsConstructor
public class EncryptPassword implements ValueMapper<User, User>{
    @Override
    public User apply(User user) {
        user.setPass(new PasswordEncryptor().encrypt(user.getPass()));
        return user;
    }
}
