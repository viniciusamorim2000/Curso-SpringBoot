package com.example.curso.resource;

import com.example.curso.entities.Category;
import com.example.curso.repositories.CategoryRepository;
import com.example.curso.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource{
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> finAll(){

        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }
    //Insere usuários no banco
    @Autowired
    private CategoryRepository repository;


    //Busca usuários por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
