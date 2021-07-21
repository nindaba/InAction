package com.yadlings.streamprocessor.Masking;

import com.yadlings.streamprocessor.avro.Purchase;
import com.yadlings.streamprocessor.avro.Rewards;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableKafkaStreams
@Log4j2
public class CardMask {
    @Value("${topic.main}")
    private String inTopic;
    @Value("${topic.maskTopic}")
    private String maskTopic;
    @Value("${topic.partitions}")
    private int partitions;
    @Value("${topic.replicas}")
    private int replicas;
    @Value("${topic.schema}")
    private String schema;
    @Autowired
    private StreamsBuilder Mask(StreamsBuilder builder){
        var longSerde = Serdes.Long();
        builder.stream(inTopic, Consumed.with(longSerde,purchaseSerde()))
                .mapValues(purchase -> {
                    purchase.setCard("xxxx-xxxx-xxxx-"+purchase.getCard().subSequence(12,15));
                    log.info("Masked {}",purchase);
                    return purchase;
                })
                .to(maskTopic, Produced.with(longSerde,purchaseSerde()));
        return builder;
    }

    private SpecificAvroSerde<Purchase> purchaseSerde() {
        var serde = new SpecificAvroSerde<Purchase>();
        serde.configure(
                Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema)
        ,false);
        return serde;
    }
    private SpecificAvroSerde<Rewards> rewardsSerde() {
        var serde = new SpecificAvroSerde<Rewards>();
        serde.configure(
                Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema)
                ,false);
        return serde;
    }
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder
                .name(maskTopic)
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }
}
