package com.yadlings.streamprocessor.ProcessorApi.Processor;

import com.yadlings.Domain.User;
import com.yadlings.Domain.UserCount;
import com.yadlings.streamprocessor.ProcessorApi.Punctuator.UserCountPunctuator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.state.KeyValueStore;
@Log4j2
public class UserCountProcessor extends AbstractProcessor<String, User> {
    private String storeName;
    public UserCountProcessor(String store){
        this.storeName = store;
    }
    private KeyValueStore<String,UserCount> keyValueStore;
    @Override
    public void init(ProcessorContext context) {
        int limit =10;
        super.init(context);
        keyValueStore = (KeyValueStore<String, UserCount>) context.getStateStore(storeName);
        UserCountPunctuator punctuator = new UserCountPunctuator(context(), keyValueStore, limit);
        context().schedule(20*1000, PunctuationType.WALL_CLOCK_TIME,punctuator);
    }
    @Override
    public void process(String s, User user) {
        keyValueStore.u
        UserCount userCount = keyValueStore.get(user.getRole());
        if(userCount!=null) userCount.setCount(userCount.getCount()+1);
        else userCount = new UserCount(user.getRole(), 1);
        keyValueStore.delete(userCount.getRole());
        keyValueStore.put(userCount.getRole(), userCount);
        log.info("Counted {}",userCount);
//        context().forward(userCount.getRole(), userCount,"addToCount");
    }
}
