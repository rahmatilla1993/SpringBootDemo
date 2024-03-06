package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.ItemDao;
import com.example.springbootdemo.dao.StoreDao;
import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.Store;
import com.example.springbootdemo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemDao itemDao;

    @Autowired
    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }


    @GetMapping(value = "/all")
    public HttpEntity<List<Item>> getAll() {
        return ResponseEntity.ok(itemDao.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Item> getOne(@PathVariable("id") int id) {
        Item item = itemDao.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Item with '%s' id not found".formatted(id))
                );
        return ResponseEntity.ok(item);
    }

    @PostMapping("/add")
    public HttpEntity<?> save(@RequestBody Item item, @RequestParam("store_id") int store_id) {
        itemDao.save(item, store_id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Item created");
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable("id") int id, @RequestBody Item item,
                              @RequestParam("store_id") int store_id) {
        item.setId(id);
        itemDao.edit(item, store_id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Item edited");
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") int id) {
        itemDao.delete(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Item deleted");
    }
}
