package com.yadlings.topologytest.Streams;

import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Domain.UserRoleCount;
import com.yadlings.topologytest.Processor.EncryptPassword;
import com.yadlings.topologytest.Processor.UserCounter;
import com.yadlings.topologytest.Serdes.SerDes;
import com.yadlings.topologytest.Utils.PasswordEncryptor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserStream {
    @Value("${user.counter.stata-store-name}")
    private String counterState;

    //    @Autowired
    public StreamsBuilder builder(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(counterState);
        StoreBuilder<KeyValueStore<String, UserRoleCount>> storeBuilder = Stores.keyValueStoreBuilder(
                storeSupplier,
                Serdes.String(),
                SerDes.UseCountSerde()
        );
        storeBuilder.withLoggingEnabled(Map.of(
                "retention.ms","259200000",
                "cleanup.policy","compact,delete"
        ));
        streamsBuilder.addStateStore(storeBuilder);
        KStream<String, User> secure = streamsBuilder
                .stream("RegisterUsers", Consumed.with(Serdes.String(), SerDes.UserSerde()))
                .mapValues(new EncryptPassword()::apply);
        secure
                .to("SecuredUsers", Produced.with(Serdes.String(),SerDes.UserSerde()));
        KStream<String, UserRoleCount> countKStream = secure
                .transformValues(() -> new UserCounter(counterState), counterState);
        countKStream
                .to("UserCount", Produced.with(Serdes.String(),SerDes.UseCountSerde()));
        countKStream
                .groupByKey()
                .reduce((countx,county)-> {
                    if(countx.getCount()>county.getCount()) return countx;
                    else return county;
                })
                .toStream()
                .to("HigherCount", Produced.with(Serdes.String(),SerDes.UseCountSerde()));

        return streamsBuilder;
    }

}
