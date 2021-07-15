package com.yadlings.Masking;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafkaStreams
public class CardMask {
    @Value("${topic.name}")
    private String inTopic;
    @Autowired
    private StreamsBuilder Mask(StreamsBuilder builder){
        builder.stream()
        return builder;
    }

}
