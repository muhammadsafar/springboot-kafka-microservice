package com.msbahrddin.orderservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);

	public static List<String> messageList = new ArrayList<>();

	@Override
	public void onMessage(Message message, byte[] arg1) {

		logger.info("message receive " + message);

		messageList.add(message.toString());

		System.out.println("message added received : " + message.toString());
	}

}
