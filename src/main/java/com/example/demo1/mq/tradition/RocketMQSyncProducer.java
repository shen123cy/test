package com.example.demo1.mq.tradition;

/**
 * 传统生成  见官方demo
 */
public class RocketMQSyncProducer {

/*    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        //please_rename_unique_group_name_p
        DefaultMQProducer producer = new
                DefaultMQProducer("CID_UNIQUE_GROUP_NAME_1");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.

            JSONObject j = new JSONObject();
            j.put("name", "tom");
            j.put("age", 18);
//            Message msg = new Message("TopicTest",  j.toString().getBytes("UTF-8"));
            Message msg = new Message("TOPIC_1", "TAG_1", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
            System.out.println("============" + sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }*/
}
