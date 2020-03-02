package com.example.curso.resource;

import com.example.curso.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> finAll(){
        User u = new User(1L,"maria", "maria@gmailcom","9999999999","12345");
        return ResponseEntity.ok().body(u);
    }
}
