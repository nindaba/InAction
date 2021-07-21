package com.yadlings.inactionproducer;

import com.yadlings.avro.StockTransaction;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;


@Service
@EnableKafka
@Log4j2
public class ProducerService {
    @Bean
    public void send() {
        Arrays.asList(
                new StockTransaction("A","B",100,"1","8",40.0,new Date().getTime(),true),
                new StockTransaction("B","A",50,"15","2",10.0,new Date().getTime(),true),
                new StockTransaction("C","C",100,"41","1",45.0,new Date().getTime(),true),
                new StockTransaction("D","C",70,"21","12",3.5,new Date().getTime(),true),
                new StockTransaction("A","A",40,"11","B",25.21,new Date().getTime(),true),
                new StockTransaction("C","B",30,"1","2",0.9,new Date().getTime(),true),
                new StockTransaction("C","B",100,"12","2",4.5,new Date().getTime(),true)
//              new Purchase( new Long(1),new Long(1),"3467-4787-6534-8745",new Long(15),new Long(1),new Double(120), new Date().getTime())  ,
//              new Purchase( new Long(2),new Long(1),"4765-7349-7345-2525",new Long(415),new Long(2),new Double(214),new Date().getTime())  ,
//              new Purchase( new Long(3),new Long(2),"5245-8783-4245-5245",new Long(15),new Long(2),new Double(340),new Date().getTime())  ,
//              new Purchase( new Long(4),new Long(1),"5425-4733-2654-4685",new Long(13),new Long(2),new Double(42),new Date().getTime())  ,
//              new Purchase( new Long(4),new Long(3),"2767-6542-6722-7262",new Long(13),new Long(1),new Double(234),new Date().getTime())  ,
//              new Purchase( new Long(5),new Long(3),"2542-8734-8463-0873",new Long(62),new Long(2),new Double(532),new Date().getTime())
        ).forEach(this::send);
    }
    @Value("${topic.name}")
    private String topic;
    @Value("${topic.partitions}")
    private int partitions;
    @Value("${topic.replicas}")
    private short replicas;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private void send(StockTransaction purchase){
        var record = new ProducerRecord<CharSequence,StockTransaction>(topic,purchase.getIndustry().toString(),purchase);
        kafkaTemplate.send(record).addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("The Purchase could not be written due {}",throwable);
            }

            @Override
            public void onSuccess(Object o) {
                log.info("Successfully written {}",o);
            }
        });
    }
    @Bean
    public NewTopic newTopic(){
        return new NewTopic(topic,partitions,replicas);
    }
}
