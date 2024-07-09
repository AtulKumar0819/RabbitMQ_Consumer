package com.poc.rabbbitMQconsumer.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poc.rabbbitMQconsumer.entity.Product;
import com.poc.rabbbitMQconsumer.repository.ConsumerRepository;
@Service
public class ConsumerService {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	

	@Value("${java.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${java.rabbitmq.routingkey1}")
	private String routingkey1;
	
	@Value("${java.rabbitmq.routingkey}")
	private String routingkey;
	
	@Autowired
	private ConsumerRepository repo;
	
	
  	@RabbitListener(queues = "${java.rabbitmq.queue}")
	public void addProduct(Product newproduct) {
  System.out.println("Message recieved successfully"+ newproduct);
      repo.save(newproduct);
	}
  	

@RabbitListener(queues = "${java.rabbitmq.queue}")
public void deleteProduct(int id) {
	System.out.println("Deleting the following data ");
	repo.deleteById(id);
}
	
	
	
	
}
