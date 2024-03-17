package com.example.springbootdemo.async_execution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public HttpEntity<List<Product>> getAll() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Product>> completableFuture = productService.getAll();
        return ResponseEntity.ok(completableFuture.get());
    }

    @GetMapping("/{id}")
    public HttpEntity<Product> getOne(@PathVariable("id") int id) throws ExecutionException, InterruptedException {
        CompletableFuture<Product> completableFuture = productService.getOne(id);
        return ResponseEntity.ok(completableFuture.get());
    }

    @PostMapping
    public HttpEntity<Boolean> save(@RequestBody Product product) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> completableFuture = productService.save(product);
        return ResponseEntity.ok(completableFuture.get());
    }

    @PutMapping
    public HttpEntity<Boolean> edit(@RequestBody Product product) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> completableFuture = productService.edit(product);
        return ResponseEntity.ok(completableFuture.get());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") int id) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> completableFuture = productService.delete(id);
        return ResponseEntity.ok(completableFuture.get());
    }
}
