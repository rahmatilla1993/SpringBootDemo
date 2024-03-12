package com.example.springbootdemo.controller;

import com.example.springbootdemo.domains.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item/*")
public class ItemController {

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Item> update(@RequestBody Item item) {
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
    }

    @GetMapping("get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Item(id, "Swagger", "Lorem Ipsum", 216.86D), HttpStatus.OK);
    }
}
