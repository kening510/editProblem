package com.example.productscrud.controller;

import com.example.productscrud.domain.Products;
import com.example.productscrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Products> productsList = productService.listAll();
        model.addAttribute("listProducts",productsList);
        return "index";
    }
    @GetMapping("/new")
    public String showProductPage(Model model){
        Products products = new Products();
        model.addAttribute("theProduct",products);
        return "new_product";
    }
    @PostMapping("/save")
    public String saveProducts(Products products, BindingResult bindingResult){ // BindingResult???
        if(bindingResult.hasErrors()){
            return "error"; // static page
        }
        productService.save(products);

        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable(name = "id") int id, Model model){
        Optional<Products> products = productService.findProduct(id);
        if(products.isPresent()){
            model.addAttribute("product_to_edit",products.get());
            return "edit_product";
        }
        else{
            return "error";
        }

    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.delete(id);
        return "redirect:/";
    }



}
