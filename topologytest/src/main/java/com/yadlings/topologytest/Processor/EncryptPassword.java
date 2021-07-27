package com.yadlings.topologytest.Processor;

import com.yadlings.topologytest.Domain.User;
import org.apache.kafka.streams.kstream.KeyValueMapper;

public class EncryptPassword implements KeyValueMapper<Integer,User, User> {
    @Override
    public User apply(Integer integer, User user) {
        user.
    }
}
