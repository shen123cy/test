package com.example.demo1.mq;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@Slf4j
@RocketMQTransactionListener(txProducerGroup = "p-group")
public class RocketMQTransactionListenerImpl implements RocketMQLocalTransactionListener {

    //执行本地事务方法
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        //执行本地事务 返回RocketMQLocalTransactionState

        /*COMMIT,
          ROLLBACK,
          UNKNOWN*/
        log.info("executeLocalTransaction======================message:{},o:{}", message, o);
        return RocketMQLocalTransactionState.COMMIT;
    }


    //实现本地事务回查的逻辑,并返回本地事务执行状态
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("checkLocalTransaction======================message:{}", message);
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
