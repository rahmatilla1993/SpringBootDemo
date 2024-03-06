package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.StoreDao;
import com.example.springbootdemo.domain.Store;
import com.example.springbootdemo.exception.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreDao storeDao;

    public StoreController(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @GetMapping(value = "/all")
    public HttpEntity<List<Store>> getAll() {
        return ResponseEntity.ok(storeDao.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Store> getOne(@PathVariable("id") int id) {
        Store store = storeDao.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Store with '%s' id not found".formatted(id))
                );
        return ResponseEntity.ok(store);
    }

    @PostMapping("/add")
    public HttpEntity<Integer> save(@RequestBody Store store) {
        int savedID = storeDao.save(store);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedID);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable("id") int id, @RequestBody Store store) {
        storeDao.edit(store, id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Store edited");
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") int id) {
        storeDao.delete(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Store deleted");
    }
}
