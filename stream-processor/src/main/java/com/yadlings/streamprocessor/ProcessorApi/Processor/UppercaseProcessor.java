package com.yadlings.streamprocessor.ProcessorApi.Processor;

import lombok.NoArgsConstructor;
import org.apache.kafka.streams.processor.AbstractProcessor;
@NoArgsConstructor
public class UppercaseProcessor extends AbstractProcessor<String,String> {
    @Override
    public void process(String s, String s2) {
        context().forward(s,s2.toUpperCase());
    }
}
