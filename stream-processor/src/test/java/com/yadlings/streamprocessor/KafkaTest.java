package com.yadlings.streamprocessor;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;
import java.util.Properties;

public class KafkaTest {
    private TopologyTestDriver topologyTestDriver;
    @BeforeAll
    public void setUp(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        Topology topology = streamsBuilder.build();
        Properties properties = new Properties();
        properties.putAll(Map.of(
                StreamsConfig.APPLICATION_ID_CONFIG,"TestTopologyApplication_0_0",
                StreamsConfig.CLIENT_ID_CONFIG,"TestTopologyClient_0_0",
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"A:9090,B:9091,C:9092"
        ));
        topologyTestDriver = new TopologyTestDriver(topology,properties)
    }
}
