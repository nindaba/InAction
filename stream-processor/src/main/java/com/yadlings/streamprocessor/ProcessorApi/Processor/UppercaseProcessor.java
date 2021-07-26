package com.yadlings.streamprocessor.ProcessorApi.Processor;

import lombok.NoArgsConstructor;
import org.apache.kafka.streams.processor.AbstractProcessor;
@NoArgsConstructor
public class UppercaseProcessor extends AbstractProcessor<String,String> {
    @Override
    public void process(String s, String s2) {
        if (s2.equals("yes")) {
            context().forward(s,s2.toUpperCase(),"saveUpperCase");
        }
        else{
            System.out.println("no");
            context().forward(s,s2.toUpperCase(),"saveUpperCase1");
        }
    }
}
