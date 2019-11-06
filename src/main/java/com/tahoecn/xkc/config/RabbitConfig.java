package com.tahoecn.xkc.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.tahoecn.xkc.common.constants.MQConstant;

@Configuration
public class RabbitConfig {

	private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

	@Autowired
	private Environment env;
	//
	// @Autowired
	// private CachingConnectionFactory connectionFactory;

	@Autowired
	private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

	@Bean("connectionFactory")
	public CachingConnectionFactory outerRabbitConfiguration(@Value("${spring.rabbitmq.addresses}") String addresses,
			@Value("${spring.rabbitmq.username}") String username,
			@Value("${spring.rabbitmq.password}") String password,
			@Value("${spring.rabbitmq.virtual-host}") String virtualHost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		return connectionFactory;
	}

	@Bean
	public Queue placeOrderQueue() {
		return new Queue(MQConstant.PLACE_ORDER_QUEUE, true);
	}

	@Bean
	public DirectExchange orderExchange() {
		return new DirectExchange(MQConstant.ORDER_EXCHANGE, true, false);
	}

	@Bean
	public Binding orderBinding() {
		return BindingBuilder.bind(placeOrderQueue()).to(orderExchange()).with(MQConstant.ORDER_KEY);
	}

	// 信道配置
	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
	}

	/********************* 业务队列定义与绑定 *****************/
	@Bean
	public Queue queue() {
		Queue queue = new Queue(MQConstant.MASSAGE_QUEUE_NAME, true);
		return queue;
	}

	@Bean
	public Binding binding() {
		// 队列绑定到exchange上，再绑定好路由键
		return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.MASSAGE_QUEUE_NAME);
	}

	/********************* 业务队列定义与绑定 *****************/

	// 下面是延迟队列的配置
	// 转发队列
	@Bean
	public Queue repeatTradeQueue() {
		Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME, true, false, false);
		return queue;
	}

	// 绑定转发队列
	@Bean
	public Binding drepeatTradeBinding() {
		return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange())
				.with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
	}

	// 死信队列 -- 消息在死信队列上堆积，消息超时时，会把消息转发到转发队列，转发队列根据消息内容再把转发到指定的队列上
	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);
		arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
		Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, true, false, false, arguments);
		return queue;
	}

	// 绑定死信队列
	@Bean
	public Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange())
				.with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
	}

	/**
	 * 单一消费者
	 * 
	 * @return
	 */
	@Bean(name = "singleListenerContainer")
	public SimpleRabbitListenerContainerFactory listenerContainer(
			@Qualifier("connectionFactory") CachingConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setConcurrentConsumers(1);
		factory.setMaxConcurrentConsumers(1);
		factory.setPrefetchCount(1);
		factory.setTxSize(1);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}

	/**
	 * 多个消费者
	 * 
	 * @return
	 */
	@Bean(name = "multiListenerContainer")
	public SimpleRabbitListenerContainerFactory multiListenerContainer(
			@Qualifier("connectionFactory") CachingConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factoryConfigurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency", int.class));
		factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency", int.class));
		factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch", int.class));
		return factory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactory") CachingConnectionFactory connectionFactory) {
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log
				.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange,
					String routingKey) {
				log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey,
						replyCode, replyText, message);
			}
		});
		return rabbitTemplate;
	}
}