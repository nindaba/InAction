package com.yadlings.streamprocessor.ProcessorApi.Processor;

import com.yadlings.avro.StockTransaction;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class SymbolProcessor extends AbstractProcessor<String, StockTransaction> {
    @Override
    public void process(String s, StockTransaction stockTransaction) {

    }

    @Override
    public void init(ProcessorContext context) {
        super.init(context);
    }
}
