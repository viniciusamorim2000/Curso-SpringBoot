package com.example.curso.resource;


import com.example.curso.entities.Order;
import com.example.curso.entities.User;
import com.example.curso.entities.enums.OrderStatus;
import com.example.curso.repositories.OrderRepository;
import com.example.curso.repositories.UserRepository;
import com.example.curso.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    @Autowired
    private OrderService service;

    //Insere usuários no banco
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    //Busca usuários por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<Order>> finAll(){

        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }




}
