/**  
 * @Title:  UcReceiver.java   
 * @Package com.tahoecn.xkc.common.ucapi 
 * @author: wq_qycc
 * @date:   2019年8月5日
 * @version V1.0 
 */
package com.tahoecn.xkc.common.ucapi;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.mapper.uc.CsUcUserMapper;

/**
 * TODO UC消息队列 消费方
 * 
 * @ClassName: UcReceiver
 * @author: wq_qycc
 * @date: 2019年8月5日
 */
@Component
public class UcReceiver {
	private static final Log log = LogFactory.get();
	
	@Autowired
	CsUcUserMapper csUcUserMapper;

	@Bean("ucRabbitConnectionFactory")
	public ConnectionFactory outerRabbitConfiguration(@Value("${spring.rabbitmq.uc.host}") String host,
			@Value("${spring.rabbitmq.uc.port}") int port, @Value("${spring.rabbitmq.uc.username}") String username,
			@Value("${spring.rabbitmq.uc.password}") String password,
			@Value("${spring.rabbitmq.uc.virtual-host}") String virtualHost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		return connectionFactory;
	}

	@Bean("ucRabbitFactory")
	public SimpleRabbitListenerContainerFactory outerRabbitFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("ucRabbitConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@RabbitListener(containerFactory = "ucRabbitFactory", bindings = @QueueBinding(exchange = @Exchange(value = "queue_ydanch"), value = @Queue(value = "queue_ydanch", durable = "true", exclusive = "false", autoDelete = "fasle")))
	@RabbitHandler
	public void onOrderMessage(@Headers Map<String, Object> headers, Map<String, Object> ea, Channel channel) {
		try {
			JSONObject res = JSONObject.parseObject(ea.get("Body").toString());
			JSONObject data = JSONObject.parseObject(res.getString("result"));
			data.put("dataSid", res.get("dataSid"));
			data.put("opType", res.get("opType"));
			data.put("dataType", res.get("dataType"));
			data.put("nanoTime", res.get("nanoTime"));
			data.put("result", res.get("result"));
			if ("uc_user".equals(res.get("dataType"))) {
				csUcUserMapper.TaskUCUserChangeDetail_Insert(data);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			long deliveryTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
			try {
				channel.basicAck(deliveryTag, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
