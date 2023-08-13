package com.example.demo1.mq.tradition;

/**
 * 传统消费
 */
public class RocketMQConsumer {

 /*   public static void main(String[] args) throws InterruptedException, MQClientException {

        // Instantiate with specified consumer group name.
        //please_rename_unique_group_name_c
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CID_UNIQUE_GROUP_NAME_1");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TOPIC_1", "*");

        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                System.out.println("=================" + msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }*/
}
