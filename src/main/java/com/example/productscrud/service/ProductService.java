package com.example.productscrud.service;

import com.example.productscrud.domain.Products;
import com.example.productscrud.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Transactional
    public void save(Products products){
        productRepository.save(products);
    }

    public List<Products> listAll(){
        return productRepository.findAll();
    }

    public Optional<Products> findProduct(long id){
        return productRepository.findById(id);
    }
    @Transactional
    public void delete(long id){
        productRepository.deleteById(id);
    }
}
