package com.yadlings.topologytest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;

@SpringBootTest
@Log4j2
class TopologytestApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Test
	void check(){
		log.info(Instant.now().getLong(ChronoField.INSTANT_SECONDS));
		log.info(Instant.now().getLong(ChronoField.NANO_OF_SECOND));
	}

}
