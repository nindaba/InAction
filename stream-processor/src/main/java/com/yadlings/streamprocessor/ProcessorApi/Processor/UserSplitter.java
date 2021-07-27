package com.yadlings.streamprocessor.ProcessorApi.Processor;

import com.yadlings.Domain.User;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.processor.AbstractProcessor;
@NoArgsConstructor
@Log4j2
public class UserSplitter extends AbstractProcessor<String, User> {
    @Override
    public void process(String s, User user) {
        log.info("COUNT {}",user.getRole());
        if(user.getRole().equals("Admin"))
            context().forward(s,user,"addToAdmin");
        else
            context().forward(s,user,"addToLocal");
    }
}
