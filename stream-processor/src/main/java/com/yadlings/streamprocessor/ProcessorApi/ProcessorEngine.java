package com.yadlings.streamprocessor.ProcessorApi;

import com.yadlings.Serializers.DomainSerde;
import com.yadlings.Serializers.UserTopologies;
import com.yadlings.avro.StockTransaction;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UppercaseProcessor;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UserCountProcessor;
import com.yadlings.streamprocessor.ProcessorApi.Processor.UserSplitter;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
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

import java.util.Map;

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
    @Value("${topologyTopics.deltaStore}")
    private String stockDeltaState;
    @Value("${stockTopics.stockTransactions}")
    private String transactionsTopic;
    @Value("${stockTopics.stockDelta}")
    private String stockDelta;
    @Value("${userTopics.registered}")
    private String userRegisterTopic;
    @Value("${userTopics.user_admin}")
    private String userAdmin;
    @Value("${userTopics.user_local}")
    private String userLocal;
    @Value("${userTopics.user_count}")
    private String userCount;

    @Bean public NewTopic VolumeTopic(){
        return new NewTopic(stockDelta,partitions,replicas);
    }
    @Bean public NewTopic userCountTopic(){
        return new NewTopic(userCount,partitions,replicas);
    }
    @Bean public NewTopic adminTopic(){
        return new NewTopic(userAdmin,partitions,replicas);
    }
    @Bean public NewTopic localTopic(){
        return new NewTopic(userLocal,partitions,replicas);
    }

    @Autowired
    private Topology userProcess(StreamsBuilder builder){
        String userCounter = "UserCounter";
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(userCounter);
        Stores.keyValueStoreBuilder(storeSupplier,Serdes.String(),DomainSerde.userCountSerde());
        return builder.build()
                .addSource("receiveUsers",Serdes.String().deserializer(),DomainSerde.userSerde().deserializer(),userRegisterTopic)
                .addProcessor("splitUsers", UserSplitter::new,"receiveUsers")
                .addProcessor("userCount", ()-> new UserCountProcessor(userCounter),"receiveUsers")
                .addSink("addToLocal",userLocal,Serdes.String().serializer(),DomainSerde.userSerde().serializer(),"splitUsers")
                .addSink("addToLocal",userLocal,Serdes.String().serializer(),DomainSerde.userSerde().serializer(),"splitUsers")
                .addSink("addToCount",userAdmin,Serdes.String().serializer(),DomainSerde.userCountSerde().serializer(),"userCount");
    }
























//    @Autowired
    public Topology Processor(StreamsBuilder builder){
        KeyValueBytesStoreSupplier wordCount = Stores.inMemoryKeyValueStore("WordCount");
        StoreBuilder<KeyValueStore<String, Integer>> storeBuilder = Stores.keyValueStoreBuilder(wordCount, Serdes.String(), Serdes.Integer());
        return builder.build()
                .addSource("wordsSource", Serdes.String().deserializer(), Serdes.String().deserializer(), lower)
                .addStateStore(storeBuilder,"StateStore")
                .addProcessor("upperCase", UppercaseProcessor::new, "wordsSource")
                .addSink("saveUpperCase",upper, Serdes.String().serializer(),Serdes.String().serializer(), "upperCase");
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
