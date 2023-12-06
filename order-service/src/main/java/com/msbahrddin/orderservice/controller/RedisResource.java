package com.msbahrddin.orderservice.controller;

import com.msbahrddin.basedomains.dto.Message;
import com.msbahrddin.orderservice.config.RedisMessagePublisher;
import com.msbahrddin.orderservice.config.RedisMessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisResource {

    private static Logger logger = LoggerFactory.getLogger(RedisResource.class);

    private RedisMessageSubscriber data;

    public RedisResource(RedisMessageSubscriber data) {
        super();
        this.data = data;
    }

    @Autowired
    private RedisMessagePublisher publisher;

    @PostMapping("/publish")
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        logger.info("publishing : {} ", message);

        publisher.publish(message.toString());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/subscribe")
    public List<String> getMessages() {

        return RedisMessageSubscriber.messageList;
    }

}
