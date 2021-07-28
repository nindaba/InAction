package com.yadlings.topologytest.Processor;

import com.yadlings.topologytest.Domain.User;
import com.yadlings.topologytest.Domain.UserRoleCount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Value;
@Log4j2
public class UserCounter implements ValueTransformer<User, UserRoleCount> {
    private String counterState;
    public UserCounter(String name){
        this.counterState = name;
    }
    private KeyValueStore<String,UserRoleCount> store;
    @Override
    public void init(ProcessorContext processorContext) {
        store = processorContext.getStateStore(counterState);
    }

    @Override
    public UserRoleCount transform(User user) {
        UserRoleCount userRoleCount = store.get(user.getRole().toString());
        if(userRoleCount == null) userRoleCount = new UserRoleCount(user.getRole(),0);
        userRoleCount.setCount(userRoleCount.getCount()+1);
        log.info("IS COUNT STORED {}",userRoleCount);
        return userRoleCount;
    }

    @Override
    public void close() {

    }
}
