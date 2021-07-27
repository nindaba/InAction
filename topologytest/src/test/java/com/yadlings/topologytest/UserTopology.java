package com.yadlings.topologytest;

import com.yadlings.topologytest.DataGenerator.UserGenerator;
import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Serdes.SerDes;
import com.yadlings.topologytest.Streams.UserStream;
import com.yadlings.topologytest.Utils.PasswordEncryptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@SpringBootTest
public class UserTopology {
    TopologyTestDriver topologyTestDriver;
    @BeforeEach
    void setUp(){
        UserStream userStream = new UserStream();
        Properties properties = new Properties();
        properties.putAll(
                Map.of(
                        StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"UserBroker:9090",
                        StreamsConfig.APPLICATION_ID_CONFIG,"UserProcessor1"
                        )
        );
        topologyTestDriver = new TopologyTestDriver(
                userStream.builder().build(),
                properties
                );
    }
    @Test
    void userPassword(){
        User user = new UserGenerator.UserData().getUser();
        TestRecord<String, User> record = new TestRecord<>(user.getRole().toString(), user);
        topologyTestDriver.createInputTopic(
                "RegisterUsers",
                        Serdes.String().serializer(),
                        SerDes.UserSerde().serializer()
        ).pipeInput(record);
        ProducerRecord<String, User> output = topologyTestDriver.readOutput(
                "SecuredUsers",
                Serdes.String().deserializer(),
                SerDes.UserSerde().deserializer()
        );
        user.setPass(new PasswordEncryptor().encrypt(user.getPass()));
        assertThat(output.value(),equalTo(user));
    }
}
