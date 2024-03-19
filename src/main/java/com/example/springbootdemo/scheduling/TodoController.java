package com.example.springbootdemo.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public HttpEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Todo> getOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(todoService.getOne(id));
    }

    @GetMapping("/done/{id}")
    public HttpEntity<Void> setDone(@PathVariable("id") int id) {
        todoService.setDone(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public HttpEntity<Boolean> save(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.save(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<Void> edit(TodoDto dto, @PathVariable("id") int id) {
        todoService.edit(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(todoService.delete(id));
    }
}
