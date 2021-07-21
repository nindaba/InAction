package com.yadlings.streamprocessor.Market;

import com.yadlings.avro.Purchase;
import com.yadlings.avro.Rewards;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
@EnableKafkaStreams
@Log4j2
public class CardMask {
    @Value("${topic.main}")
    private String inTopic;
    @Value("${topic.bonus}")
    private String bonus;
    @Value("${topic.maskTopic}")
    private String maskTopic;
    @Value("${topic.storeone}")
    private String storeone;
    @Value("${topic.storetwo}")
    private String storetwo;
    @Value("${topic.partitions}")
    private int partitions;
    @Value("${topic.replicas}")
    private short replicas;
    @Value("${topic.schema}")
    private String schema;
    @Value("${state.rewardState}")
    private String rewardState;
    @Value("${topic.reward}")
    private String reward;

    @Value("${state.retention.ms}")
    private String retentionMs;
    @Value("${state.retention.byte}")
    private String retentionBytes;
    @Value("${state.cleanup.policy}")
    private String policy;
    @Value("${topic.discount}")
    private String discoutTopic;

    @Autowired
    private StreamsBuilder Mask(StreamsBuilder builder){
        var longSerde = Serdes.Long();
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(rewardState);
        StoreBuilder<KeyValueStore<Long, Rewards>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, longSerde, rewardsSerde());
        storeBuilder.withLoggingEnabled(Map.of(
                "retention.ms", retentionMs,
                "retention.bytes", retentionBytes,
                "cleanup.policy",policy
        ));
        builder.addStateStore(storeBuilder);
        KStream<Long, Purchase> transaction = builder.stream(inTopic, Consumed.with(longSerde, purchaseSerde()));
        KStream<Long, Purchase> maskStream = transaction.mapValues(purchase -> {
            purchase.setCard("xxxx-xxxx-xxxx-" + purchase.getCard().subSequence(15, 19));
            return purchase;
        });
        maskStream
                .to(maskTopic, Produced.with(longSerde,purchaseSerde()));
        maskStream
                .transformValues(()-> new RewardProcessor(rewardState),rewardState)
                .to(reward,Produced.with(longSerde,rewardsSerde()));
        KStream<Long,Purchase>[] store = maskStream
                .selectKey((key,purchase)->purchase.getCustomerId())
                .branch((customerId,purchase)-> purchase.getStoreId()==1,(customerId,purchase)-> purchase.getStoreId()==2);
        JoinWindows joinWindows = JoinWindows.of(2 * 60 * 1000);
        store[0].to(storeone,Produced.with(longSerde,purchaseSerde()));
        store[1].to(storetwo,Produced.with(longSerde,purchaseSerde()));
        KStream<Long, Purchase> discount = store[0].join(store[1],new Discount(), joinWindows, Joined.with(longSerde,purchaseSerde(),purchaseSerde()));
        discount.to(discoutTopic,Produced.with(longSerde,purchaseSerde()));
        return builder;
    }
    private SpecificAvroSerde<Purchase> purchaseSerde() {
        var serde = new SpecificAvroSerde<Purchase>();
        serde.configure(Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema),false);
        return serde;
    }
    private SpecificAvroSerde<Rewards> rewardsSerde() {
        var serde = new SpecificAvroSerde<Rewards>();
        serde.configure(Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema),false);
        return serde;
    }
    @Bean
    public NewTopic maskTopic(){
        return new NewTopic(maskTopic,partitions,replicas);
    }
    @Bean
    public NewTopic firstStoreTopic(){
        return new NewTopic(storeone,partitions,replicas);
    }
    @Bean
    public NewTopic secondStoreTopic(){
        return new NewTopic(storetwo,partitions,replicas);
    }
    @Bean
    public NewTopic BonusTopic(){
        return new NewTopic(bonus,partitions,replicas);
    }
    @Bean
    public NewTopic rewardTopic(){
        return new NewTopic(reward,partitions,replicas);
    }
    @Bean
    public NewTopic discountTopic(){
        return new NewTopic(discoutTopic,partitions,replicas);
    }
}
