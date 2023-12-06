package com.msbahrddin.orderservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisMessagePublisher implements MessagePublisher {

    private RedisTemplate<String, Object> redisTemplete;

    private ChannelTopic topic;

    @Autowired
    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplete, ChannelTopic topic) {
        super();
        this.redisTemplete = redisTemplete;
        this.topic = topic;
    }

    @Override
    public void publish(String message) {
        redisTemplete.convertAndSend(topic.getTopic(), message);
    }

}
