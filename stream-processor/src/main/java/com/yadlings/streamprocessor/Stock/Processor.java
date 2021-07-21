package com.yadlings.streamprocessor.Stock;

import com.yadlings.avro.StockTransaction;
import com.yadlings.avro.StockVolume;
import com.yadlings.avro.Top;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

import static org.apache.kafka.streams.kstream.Printed.toSysOut;

@Log4j2
@Component
@EnableKafkaStreams
public class Processor {
    @Value("${stockTopics.stockTransactions}")
    private String transactionTopic;
    @Value("${topic.partitions}")
    private int partitions;
    @Value("${topic.replicas}")
    private short replicas;
    @Value("${topic.schema}")
    private String schema;
    @Value("${stockTopics.stockVolume}")
    private String volumeTopic;

//    @Bean
//    public NewTopic TransactionTopic(){
//        return new NewTopic(transactionTopic,partitions,replicas);
//    }
    @Bean
    public NewTopic VolumeTopic(){
        return new NewTopic(volumeTopic,partitions,replicas);
    }

    //@Autowired
    private StreamsBuilder reduce(StreamsBuilder builder){
        var stringSerde = Serdes.String();
        builder.stream(transactionTopic, Consumed.with(stringSerde, stockTransactionsSerde()))
                .mapValues(this::toStoreVolume)
                .groupBy((key, stockVolume) -> stockVolume.getSymbol().toString(),Grouped.with(stringSerde,stockVolumeSerde()))
                .reduce(this::reduceTransaction)
                .toStream()
//                .print(Printed.<String,StockVolume>toSysOut().withLabel("TABLE"));
                .to(volumeTopic,Produced.with(stringSerde,stockVolumeSerde()));


        return builder;
    }
    @Autowired
    private StreamsBuilder aggregator(StreamsBuilder builder){
        var stringSerde = Serdes.String();
        Comparator<StockVolume> comparator = (sv1,sv2) -> sv1.getShares()-sv2.getShares();
        Top topShares = new Top(3,new ArrayList<StockVolume>());
        builder.stream(transactionTopic, Consumed.with(stringSerde, stockTransactionsSerde()))
                .mapValues(this::toStoreVolume)
                .groupBy((key, stockVolume) -> stockVolume.getSymbol().toString(),Grouped.with(stringSerde,stockVolumeSerde()))
                .aggregate(
                        ()->topShares,
                        (k,v,agg)->agg.addTopShares(v),
                        Materialized.with(stringSerde,topSerde())
                        )
                .toStream()
                .print(Printed.<String,Top>toSysOut().withLabel("TABLE"));
//                .to(volumeTopic,Produced.with(stringSerde,stockVolumeSerde()));
        return builder;
    }


    private SpecificAvroSerde<Top> topSerde() {
        SpecificAvroSerde<Top> serde = new SpecificAvroSerde<>();
        serde.configure(Map.of(
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema
        ),false);
        return serde;
    }
    private SpecificAvroSerde<StockVolume> stockVolumeSerde() {
        SpecificAvroSerde<StockVolume> serde = new SpecificAvroSerde<>();
        serde.configure(Map.of(
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema
        ),false);
        return serde;
    }

    private StockVolume reduceTransaction(StockVolume stockVolume, StockVolume stockVolume1) {
        stockVolume.setShares(stockVolume.getShares()+stockVolume1.getShares());
        return  stockVolume;
    }

    private StockVolume toStoreVolume(String key, StockTransaction stockTransaction) {
            return new StockVolume(stockTransaction.getSymbol(), stockTransaction.getIndustry(), stockTransaction.getShares());
    }

    private SpecificAvroSerde<StockTransaction> stockTransactionsSerde() {
        SpecificAvroSerde<StockTransaction> serde = new SpecificAvroSerde<>();
        serde.configure(Map.of(
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schema
        ),false);
        return serde;
    }
}
