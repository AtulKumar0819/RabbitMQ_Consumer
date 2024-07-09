package com.poc.rabbbitMQconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poc.rabbbitMQconsumer.entity.Product;
import com.poc.rabbbitMQconsumer.service.ConsumerService;

@Controller
@RequestMapping("/consumer")

public class ConsumerController {

	@Autowired
	private ConsumerService service ;
	
	@PostMapping("/add")
	public void addProduct (@RequestBody Product newproduct) {
		service.addProduct(newproduct);
	}
	  @DeleteMapping("/del/{id}")
	  private void DeleteProduct(@PathVariable("id") int id ){
	   service.deleteProduct(id);
	   }	   	
	
}
