package com.yadlings.streamprocessor.ProcessorApi.Processor;

import com.yadlings.Domain.User;
import com.yadlings.Domain.UserCount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
public class UserCountProcessor extends AbstractProcessor<String, User> {
    private String storeName;
    public UserCountProcessor(String store){
        this.storeName = store;
    }
    private KeyValueStore<String,UserCount> keyValueStore;
    @Override
    public void init(ProcessorContext context) {
        super.init(context);
        keyValueStore = (KeyValueStore<String, UserCount>) context.getStateStore(storeName);
    }

    @Override
    public void process(String s, User user) {
        UserCount userCount = keyValueStore.get(user.getRole());
        userCount = userCount==null ? new UserCount(userCount.getRole(), -1): userCount;
        userCount.setCount(userCount.getCount()+1);
        keyValueStore.put(userCount.getRole(), userCount);
        context().forward(userCount.getRole(), userCount,"addToCount");
    }
}
