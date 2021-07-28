package com.yadlings.topologytest;

import com.yadlings.topologytest.Constants.Role;
import com.yadlings.topologytest.Domain.UserRoleCount;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
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
	@Test
	void check(){
		log.info(counterState);

	}
	@Value("${user.counter-stata-store-name}")
	String counterState;
}
