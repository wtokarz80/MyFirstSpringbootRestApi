package com.company.controller.rest;

import com.company.model.Order;
import com.company.model.Product;
import com.company.repository.ClientRepository;
import com.company.repository.OrderRepository;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerRest {

    private ProductRepository productRepository;

    @Autowired
    public ProductControllerRest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(defaultValue = "name") String orderBy) {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getName));

        return products;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveProduct(@RequestBody Product product) {
        productRepository.save(product);

    }
}
