package com.Lins.product_manager_study.controllers;


import com.Lins.product_manager_study.domain.Product;
import com.Lins.product_manager_study.domain.ProductRepository;
import com.Lins.product_manager_study.domain.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity showAllProducts(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Validated RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok("Product created successfully");
    }

    @PutMapping
    public ResponseEntity changeProduct(@RequestBody @Validated RequestProduct data) {
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setCategory(data.category());
            product.setPrice_in_cents( (Integer) data.price_in_cents());

            repository.save(product);

            return ResponseEntity.ok("Update ");
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id){
        if (repository.existsById(id)){
            repository.deleteById(id);

            return ResponseEntity.ok("Deleted successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
