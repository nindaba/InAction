package com.yadlings.inactionconsumer;

import com.yadlings.Domain.User;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
@Log4j2
public class InActionConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(InActionConsumerApplication.class, args);
	}
	//@KafkaListener(topics = "${topic.name}",groupId = "${topic.group-id}")
	private void Mask(ConsumerRecord<String, User> consumerRecord){
		log.info("Recieved {} ",consumerRecord.value());
	}
public class A<T>{
		public void print(T x){
			log.info("Message {} Type {}" ,x, x.getClass().getName());
		}
}
	@Bean
	public void time(){
		new A<Integer>().print(10);
	}
}
