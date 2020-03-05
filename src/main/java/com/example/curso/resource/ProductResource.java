package com.example.curso.resource;

import com.example.curso.entities.Category;
import com.example.curso.entities.Product;
import com.example.curso.repositories.CategoryRepository;
import com.example.curso.repositories.ProductRepository;
import com.example.curso.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> finAll(){

        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }
    //Insere usuários no banco
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    //Busca usuários por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
