package com.yadlings.inactionconsumer;

import com.yadlings.Domain.UserCount;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.core.GenericType;
import java.lang.reflect.ParameterizedType;
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
	public class A<T>{
		public void print(T x,Class<T> c){

			log.info("Message {} Type {}" ,x, (ParameterizedType)getClass().getGenericSuperclass());
		}
	}
	@Test
	void type(){
		new A<UserCount>().print(new UserCount("hello",10),UserCount.class);
	}
}
