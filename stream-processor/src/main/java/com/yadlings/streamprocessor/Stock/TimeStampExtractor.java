package com.yadlings.streamprocessor.Stock;

import com.yadlings.avro.StockTransaction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
public class TimeStampExtractor implements TimestampExtractor{
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long l) {
        StockTransaction transaction = (StockTransaction) consumerRecord.value();
        return transaction.getTransactionTimeStamp();
    }
}
