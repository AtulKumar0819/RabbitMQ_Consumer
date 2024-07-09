package com.poc.rabbbitMQconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.rabbbitMQconsumer.entity.Product;

public interface ConsumerRepository extends  JpaRepository<Product, Integer>{

}