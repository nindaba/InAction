package com.yadlings.streamprocessor.ProcessorApi.Punctuator;

import com.yadlings.Domain.UserCount;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

@AllArgsConstructor
@Log4j2
public class UserCountPunctuator implements Punctuator{
    private ProcessorContext context;
    private KeyValueStore<String, UserCount> keyValueStore;
    private int limit;
    @Override
    public void punctuate(long l) {
        KeyValueIterator<String, UserCount> all = keyValueStore.all();
        all.forEachRemaining(keyvalue->{
            log.info("PUNCTUATE {}",keyvalue);
            context.forward(keyvalue.key,keyvalue.value,"addToCount");
//            if(keyvalue.value.getCount()> 35
        });
    }
}
