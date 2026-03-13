package com.tarik.content_clander.controller;

import com.tarik.content_clander.model.Marchandise;
import com.tarik.content_clander.repository.MarchandiseRepository;
import com.tarik.content_clander.services.MarchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/marchandises")
public class MarchandiseController {

    @Autowired
    private MarchandiseService marchandiseService;

    @GetMapping
    public List<Marchandise> getAll() { return marchandiseService.getAllM(); }

    @GetMapping("/{id}")
    public Marchandise getById(@PathVariable Long id) {
        return marchandiseService.getMById(id);
    }

    @GetMapping("/declaration/{declId}")
    public List<Marchandise> getByDeclaration(@PathVariable Long Id) {
        return marchandiseService.getD(Id);
    }

    @PostMapping
    public ResponseEntity<Marchandise> create(@RequestBody Marchandise m) {
        return ResponseEntity.status(201).body(marchandiseService.creatMarchandise(m));
    }

    @PutMapping("/{id}")
    public Marchandise update(@PathVariable Long id, @RequestBody Marchandise m) {
        return marchandiseService.editMById(id,m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marchandiseService.deleteM(id);
        return ResponseEntity.noContent().build();
    }
}