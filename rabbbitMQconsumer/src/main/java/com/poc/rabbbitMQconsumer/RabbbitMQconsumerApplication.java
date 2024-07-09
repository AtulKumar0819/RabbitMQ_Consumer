package com.poc.rabbbitMQconsumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbbitMQconsumerApplication {
	@Value("${java.rabbitmq.queue}")
	String queueName;

	@Value("${java.rabbitmq.exchange}")
	String exchange;

	@Value("${java.rabbitmq.routingkey}")
	private String routingkey;

	@Value("${java.rabbitmq.queue1}")
	String queueName1;

	@Value("${java.rabbitmq.routingkey1}")
	private String routingkey1;

	@Value("${java.rabbitmq.queue2}")
	String queueName2;

	public static void main(String[] args) {
		SpringApplication.run(RabbbitMQconsumerApplication.class, args);
	}
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	@Bean
	Queue queue1() {
		return new Queue(queueName1, false);
	}
	@Bean
	Queue queue2() {
		return new Queue(queueName2, false);
	}


	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	@Bean
	Binding binding1(Queue queue1, DirectExchange exchange) {
		return BindingBuilder.bind(queue1).to(exchange).with(routingkey1);
	}
	@Bean
	Binding binding2(Queue queue2, DirectExchange exchange) {
		return BindingBuilder.bind(queue2).to(exchange).with(routingkey);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 *This is used to establish connection between our application and rabbitMQ*
	 * @param connectionFactory
	 * @return rabbitTemplate 
	 */
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
