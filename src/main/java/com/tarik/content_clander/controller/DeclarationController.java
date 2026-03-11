package com.tarik.content_clander.controller;

import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.repository.DeclarationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/declarations")
public class DeclarationController {

    @Autowired
    private DeclarationRepository repo;

    @GetMapping
    public List<Declaration> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Declaration getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping("/user/{userId}")
    public List<Declaration> getByUser(@PathVariable Long userId) {
        return repo.findAll().stream()
                .filter(d -> d.getUser().getId_U().equals(userId))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Declaration> create(@RequestBody Declaration d) {
        return ResponseEntity.status(201).body(repo.save(d));
    }

    @PutMapping("/{id}")
    public Declaration update(@PathVariable Long id, @RequestBody Declaration d) {
        d.setId_D(id);
        return repo.save(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}