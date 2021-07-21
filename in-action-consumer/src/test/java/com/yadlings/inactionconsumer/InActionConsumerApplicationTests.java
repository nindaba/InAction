package com.yadlings.inactionconsumer;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Log4j2
@SpringBootTest
class InActionConsumerApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test void time(){
		log.info("Time {}",new Date());
	}
}
