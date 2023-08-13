package com.example.demo1.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestKafkaConsumer {

  @KafkaListener(topics = "KAFKA_TOPIC_1", groupId = "KAFKA_GROUP_ID_1")
    public void testKafka(String in) {
        log.info("testKafka msg:{}", in);
        //do something
    }

}
