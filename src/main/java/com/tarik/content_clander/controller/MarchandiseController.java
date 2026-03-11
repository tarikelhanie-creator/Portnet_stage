package com.tarik.content_clander.controller;

import com.tarik.content_clander.model.Marchandise;
import com.tarik.content_clander.repository.MarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marchandises")
public class MarchandiseController {

    @Autowired
    private MarchandiseRepository repo;

    @GetMapping
    public List<Marchandise> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Marchandise getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping("/declaration/{declId}")
    public List<Marchandise> getByDeclaration(@PathVariable Long declId) {
        return repo.findAll().stream()
                .filter(m -> m.getDeclaration().getId_D().equals(declId))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Marchandise> create(@RequestBody Marchandise m) {
        return ResponseEntity.status(201).body(repo.save(m));
    }

    @PutMapping("/{id}")
    public Marchandise update(@PathVariable Long id, @RequestBody Marchandise m) {
        m.setId_M(id);
        return repo.save(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}