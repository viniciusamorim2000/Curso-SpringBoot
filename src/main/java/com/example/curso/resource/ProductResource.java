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
public class ProductResource implements CommandLineRunner {
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

    @Override
    public void run (String... args) throws  Exception{
        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
        List<Product> products = Arrays.asList(p1, p2, p3, p4, p5);

        repository.saveAll(products);



    }

    //Busca usuários por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value ="/productCategory" )
    public ResponseEntity<List<Void>> productCategory(){
        Category cat1=  categoryRepository.findById(1L).get();
        Category cat2=  categoryRepository.findById(2L).get();
        Category cat3=  categoryRepository.findById(3L).get();

        Product p1,p2,p3,p4,p5;

        p1=repository.findById(1L).get();
        p2=repository.findById(2L).get();
        p3=repository.findById(3L).get();
        p4=repository.findById(4L).get();
        p5=repository.findById(5L).get();

        cat1.getProducts().addAll(Arrays.asList(p2));
        cat2.getProducts().addAll(Arrays.asList(p1,p5));
        cat3.getProducts().addAll(Arrays.asList(p2,p3,p4));

        p1.getCategories().add(cat2);
        p2.getCategories().addAll(Arrays.asList(cat1,cat3));
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        repository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        return ResponseEntity.noContent().build();
    }
}
