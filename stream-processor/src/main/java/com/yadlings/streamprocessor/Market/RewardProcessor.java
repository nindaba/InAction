package com.yadlings.streamprocessor.Market;

import com.yadlings.avro.Purchase;
import com.yadlings.avro.Rewards;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Log4j2
public class RewardProcessor implements ValueTransformer<Purchase,Rewards> {

    private String storeName;
    RewardProcessor(String storeName){
        log.info("Store Name {}",storeName);
        this.storeName = storeName;
    }
    KeyValueStore<Long,Rewards> stateStore;
    private ProcessorContext processorContext;
    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
        stateStore = this.processorContext.getStateStore(storeName);
    }

    @Override
    public Rewards transform(Purchase purchase) {
        Rewards rewards = stateStore.get(purchase.getCustomerId());
        if(rewards != null){
           double currentPoints =  purchase.getTotal() / 10;
           double totalPoints = rewards.getTotal() + currentPoints;
           rewards.setCurrentPoints(currentPoints);
           rewards.setTotal(totalPoints);
           rewards.setLastTransactionDate(purchase.getTransactionDate());
           stateStore.put(rewards.getCustomerId(),rewards);
        }else{
            rewards = new Rewards(purchase.getCustomerId(), purchase.getTotal() / 10, purchase.getTotal() / 10, purchase.getTransactionDate());
            stateStore.put(purchase.getCustomerId(), rewards);
        }
        return rewards;
    }

    @Override
    public void close() {

    }
}
