package com.yadlings.streamprocessor.ProcessorApi;

import com.yadlings.Domain.User;
import com.yadlings.Domain.UserCount;
import com.yadlings.Serializers.DomainSerde;
import com.yadlings.Serializers.JsonDeserializer;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UppercaseProcessor;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UserCountProcessor;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UserSplitter;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;


@Component
@EnableKafkaStreams
@EnableKafka
@Log4j2
public class ProcessorEngine {
    @Value("${topologyTopics.lower}")
    private String lower;
    @Value("${topic.partitions}")
    private int partitions;
    @Value("${topic.replicas}")
    private short replicas;
    @Value("${topic.schema}")
    private String schema;
    @Value("${topologyTopics.upper}")
    private String upper;
    @Value("${topologyTopics.upper1}")
    private String upper1;
    @Value("${stockTopics.stockTransactions}")
    private String transactionsTopic;
    @Value("${userTopics.registered}")
    private String userRegisterTopic;
    @Value("${userTopics.user_admin}")
    private String userAdmin;
    @Value("${userTopics.user_local}")
    private String userLocal;
    @Value("${userTopics.countState}")
    private String userCounterState;
    @Value("${userTopics.user_count}")
    private String userCounter;

    @Bean public NewTopic userCountTopic(){
        return new NewTopic(userCounter,partitions,replicas);
    }
    @Bean public NewTopic adminTopic(){
        return new NewTopic(userAdmin,partitions,replicas);
    }
    @Bean public NewTopic localTopic(){
        return new NewTopic(userLocal,partitions,replicas);
    }
    @Bean public NewTopic upperTopic(){
        return new NewTopic(upper1,partitions,replicas);
    }

    @Autowired
    private Topology userProcess(StreamsBuilder builder){
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(userCounterState);
        StoreBuilder<KeyValueStore<String, UserCount>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), DomainSerde.userCountSerde());
        return builder.build()
                .addSource("receiveUsers",Serdes.String().deserializer(), DomainSerde.userSerde().deserializer(),userRegisterTopic)
                .addProcessor("splitUsers", UserSplitter::new,"receiveUsers")
                .addProcessor("userCount", ()-> new UserCountProcessor(userCounterState),"receiveUsers")
                .addStateStore(storeBuilder,"userCount")
                .addSink("addToLocal",userLocal,Serdes.String().serializer(),DomainSerde.userSerde().serializer(),"splitUsers")
                .addSink("addToAdmin",userAdmin,Serdes.String().serializer(),DomainSerde.userSerde().serializer(),"splitUsers")
                .addSink("addToCount",userCounter,Serdes.String().serializer(),DomainSerde.userCountSerde().serializer(),"userCount");
    }
























//    @Autowired
    public Topology Processor(StreamsBuilder builder){
        return builder.build()
                .addSource("wordsSource", Serdes.String().deserializer(), Serdes.String().deserializer(), lower)
                .addProcessor("upperCase", UppercaseProcessor::new, "wordsSource")
                .addSink("saveUpperCase",upper, Serdes.String().serializer(),Serdes.String().serializer(), "upperCase")
                .addSink("saveUpperCase1",upper1, Serdes.String().serializer(),Serdes.String().serializer(), "upperCase");
    }
//    @Autowired
//    private Topology stockProcessor(StreamsBuilder builder){
//        KeyValueBytesStoreSupplier stateStore = Stores.inMemoryKeyValueStore(stockDeltaState);
//        StoreBuilder<KeyValueStore<String, StockTransaction>> storeBuilder = Stores.keyValueStoreBuilder(stateStore, Serdes.String(), stockTransactionSerde());
//        return builder.build()
//                .addSource("Transactions",Serdes.String().deserializer(), stockTransactionSerde().deserializer(),transactionsTopic)
//                .addSink("StockToDisplay",Serdes.String().serializer(),)
//
//    }
}
