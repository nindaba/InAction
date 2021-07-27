package com.yadlings.topologytest.Streams;

import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Processor.EncryptPassword;
import com.yadlings.topologytest.Serdes.SerDes;
import com.yadlings.topologytest.Utils.PasswordEncryptor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class UserStream {
//    @Autowired
    public StreamsBuilder builder(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        streamsBuilder
                .stream("RegisterUsers", Consumed.with(Serdes.String(), SerDes.UserSerde()))
                .mapValues(new EncryptPassword()::apply)
                .to("SecuredUsers", Produced.with(Serdes.String(),SerDes.UserSerde()));
        return streamsBuilder;
    }



}
