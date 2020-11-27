package com.company.controller.rest;

import com.company.model.Product;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerRest {

    private final ProductService productService;

    @Autowired
    public ProductControllerRest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
