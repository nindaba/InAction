package com.yadlings.streamprocessor.ProcessorApi.Processor;

import com.yadlings.Domain.User;
import lombok.NoArgsConstructor;
import org.apache.kafka.streams.processor.AbstractProcessor;
@NoArgsConstructor
public class UserSplitter extends AbstractProcessor<String, User> {
    @Override
    public void process(String s, User user) {
        if(user.getRole().equals("Admin"))
            context().forward(s,user,"addToAdmin");
        else
            context().forward(s,user,"addToLocal");
    }
}
