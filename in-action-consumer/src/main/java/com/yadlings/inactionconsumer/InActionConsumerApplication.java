package com.yadlings.inactionconsumer;

import com.yadlings.avro.Purchase;
import com.yadlings.avro.StockVolume;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
@Log4j2
public class InActionConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(InActionConsumerApplication.class, args);
	}
	@KafkaListener(topics = "${topic.name}",groupId = "${topic.group-id}")
	private void Mask(ConsumerRecord<String, String> consumerRecord){
		log.info("Recieved {} ",consumerRecord.value());
	}
	//@KafkaListener(topics = "${topic.storeone}",groupId = "${topic.store-one-group}")
	private void StoreOne(ConsumerRecord<Long, Purchase> consumerRecord){
		log.info("StoreOne {}",consumerRecord);
	}
	//@KafkaListener(topics = "${topic.storetwo}",groupId = "${topic.store-two-group}")
	private void StoreTwo(ConsumerRecord<Long, Purchase> consumerRecord){
		log.info("StoreTwo {}",consumerRecord);
	}
}
