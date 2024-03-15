package com.example.springbootdemo.controller;

import com.example.springbootdemo.domains.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/store/*")
public class StoreController {

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Store> create(@RequestBody Store entity) {
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Store> update(@RequestBody Store entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }

    @GetMapping("get/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Store> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Store(id, "Store", ".....@gmail.com",
                20, "The point of using Lorem Ipsum is that it"), HttpStatus.OK);
    }

}
