package com.msbahrddin.orderservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter adapter) {

		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(connectionFactory);
		redisMessageListenerContainer.addMessageListener(adapter, topic());

		return redisMessageListenerContainer;
	}

	@Bean
	MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(new RedisMessageSubscriber(), "onMessage");
	}

	@Bean
	private ChannelTopic topic() {
		return new ChannelTopic("belajar_redis");

	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}

	@Bean
	MessagePublisher messagePublisher() {
		return new RedisMessagePublisher(redisTemplate(redisConnectionFactory), topic());

	}

}
