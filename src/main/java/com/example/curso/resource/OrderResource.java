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
public class OrderResource implements CommandLineRunner{
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> finAll(){

        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

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
    @GetMapping(value = "/orderUser")
    public ResponseEntity<List<Order>> orderUser(){
        User u1 = userRepository.findById(1L).get();
        User u2 = userRepository.findById(1L).get();

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAIIING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAIIING_PAYMENT, u1);

        List<Order> orders = Arrays.asList(o1, o2, o3);

        orderRepository.saveAll(orders);

        return ResponseEntity.ok().body(orders);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
