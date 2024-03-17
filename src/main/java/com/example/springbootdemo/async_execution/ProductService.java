package com.example.springbootdemo.async_execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ProductService {

    private final AtomicInteger id = new AtomicInteger(4);
    private final CopyOnWriteArrayList<Product> products = new CopyOnWriteArrayList<>(List.of(
            new Product(1, "Iphone 12 Pro", "Smartphone", 2000.0),
            new Product(2, "Acer Aspire", "Notebook", 500.0),
            new Product(3, "Nvidia GeForce 3080 TI", "Video card", 1500.0)
    ));

    @Async
    public CompletableFuture<List<Product>> getAll() {
        log.info("get all products");
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<Product> getOne(int id) {
        log.info("get product by id");
        Product productFromDb = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with '%s' id not found".formatted(id)));
        return CompletableFuture.completedFuture(productFromDb);
    }

    @Async
    public CompletableFuture<Boolean> save(Product product) {
        log.info("save product");
        product.setId(id.getAndIncrement());
        return CompletableFuture.completedFuture(products.add(product));
    }

    @Async
    public CompletableFuture<Boolean> edit(Product product) {
        log.info("edit product");
        products.stream()
                .filter(item -> item.getId() == product.getId())
                .findFirst()
                .ifPresentOrElse(prod -> {
                    prod.setName(product.getName());
                    prod.setCategory(product.getCategory());
                    prod.setPrice(product.getPrice());
                }, () -> {
                    throw new RuntimeException("Product with '%s' id not found".formatted(product.getId()));
                });
        return CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> delete(int prodId) {
        log.info("delete product");
        products.stream()
                .filter(product -> product.getId() == prodId)
                .findFirst()
                .ifPresentOrElse(products::remove, () -> {
                    throw new RuntimeException("Product with '%s' id not found".formatted(prodId));
                });
        return CompletableFuture.completedFuture(true);
    }
}
