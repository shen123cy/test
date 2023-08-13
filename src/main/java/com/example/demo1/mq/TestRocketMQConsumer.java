package com.example.demo1.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * rocketmq-spring 测试
 */
@Component
@RocketMQMessageListener(topic = "topic1", consumerGroup = "CID_UNIQUE_GROUP_NAME_1", selectorExpression = "*")
@Slf4j
public class TestRocketMQConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        log.info("TestRocketMQConsumer msg:{}", msg);
    }
}
