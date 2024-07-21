package com.learning.simran.jpaTutorial.controllers;

import com.learning.simran.jpaTutorial.entities.ProductEntity;
import com.learning.simran.jpaTutorial.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProductsByCoke(){
        return productRepository.findByTitleOrderByPrice("Coke");
    }

    //all order just use findBy
    @GetMapping(path = "/all")
    public List<ProductEntity> getAllProductsAndOrder(){
        return productRepository.findByOrderByPrice();
    }

    //all order just use findBy, loosely coupling sort
    @GetMapping(path = "/all1")
    public List<ProductEntity> getAllProductsAndOrder1(@RequestParam(defaultValue = "id") String sortBy){
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        Sort sort2 = Sort.by(Sort.Order.asc("title"), Sort.Order.desc(("price")));
        return productRepository.findBy(sort);
    }

    //example of pageable
    @GetMapping(path = "/all2")
    public Page<ProductEntity> getAllProductsPaging(){
        Pageable pageable = PageRequest.of(1, 3);
        return productRepository.findAll(pageable);
    }

    //example 2 of pageable
    @GetMapping(path = "/all3")
    public Page<ProductEntity> getAllProductsPaging(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findAll(pageable);
    }
}
