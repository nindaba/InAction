package com.yadlings.streamprocessor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@SpringBootTest
class StreamProcessorApplicationTests {
	@Test
	void contextLoads() {
	}
	@Test
	void topic(@Value("${stockTopics.stockTransactions}") String to){
		log.info("TOPIC {}",to);
	}
}
