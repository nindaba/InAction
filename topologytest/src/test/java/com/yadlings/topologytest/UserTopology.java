package com.yadlings.topologytest;

import com.yadlings.topologytest.Constants.Role;
import com.yadlings.topologytest.DataGenerator.UserGenerator;
import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Domain.UserRoleCount;
import com.yadlings.topologytest.Processor.UserCounter;
import com.yadlings.topologytest.Serdes.SerDes;
import com.yadlings.topologytest.Streams.UserStream;
import com.yadlings.topologytest.Utils.PasswordEncryptor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@Log4j2
public class UserTopology {
    TopologyTestDriver topologyTestDriver;
    @Value("${user.counter.stata-store-name}")
    String counterState;
    @Autowired
    UserStream userStream;
    @BeforeEach
    void setUp(){
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
        log.info(output.value());
        assertThat(output.value(),equalTo(user));
    }
    @Test
    void createUser(){
        User user = new UserGenerator.UserData().getUser();
        log.info("USER {}",user);
    }
    @Test
    void countUsers(){
        List<User> users = new UserGenerator.UserData().getUsers();
        List<TestRecord<String, User>> records = users.stream()
                .map(user -> new TestRecord<>(user.getRole().toString(), user))
                .collect(Collectors.toList());
        topologyTestDriver.createInputTopic(
                "RegisterUsers",
                Serdes.String().serializer(),
                SerDes.UserSerde().serializer()
        ).pipeRecordList(records);
//        topologyTestDriver.getKeyValueStore(counterState)
//                .all()
//                .forEachRemaining(keyValue->log.info("This is my keyvalue",keyValue));
        ProducerRecord<String, UserRoleCount> output = topologyTestDriver.readOutput(
                "UserCount",
                Serdes.String().deserializer(),
                SerDes.UseCountSerde().deserializer()
        );
        KeyValueStore<String, UserCounter> stateStore = topologyTestDriver.getKeyValueStore(counterState);
        log.info("STRORE {}",stateStore.get(Role.ADMIN.toString()));
        log.info("STRORE {}",stateStore.get(Role.STUDENT.toString()));
        log.info("STRORE {}",stateStore.get(Role.EMPLOYEE.toString()));
    }
    @Test
    void countStateName(){
        log.info(counterState);

    }
}
