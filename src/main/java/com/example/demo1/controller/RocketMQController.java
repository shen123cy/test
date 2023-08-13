package com.example.demo1.controller;


import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rocket")
public class RocketMQController {

    /*@Autowired
    private RocketMQTemplate rocketMQTemplate;*/

    @GetMapping("sent")
    public void sentFoo(@RequestParam("msg") String msg) {
        Message<String> m1 = MessageBuilder.withPayload(msg).build();
        Map param = new HashMap<>();
        param.put("name", "jack");
//        rocketMQTemplate.sendMessageInTransaction("p-group", "topic_1", m1, param);
    }

}
