package com.tarik.content_clander.controller;

import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.services.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/declarations")
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;

    @GetMapping
    public List<Declaration> getAll() { return declarationService.getAllD();}

    @GetMapping("/{id}")
    public Declaration getById(@PathVariable Long id) {
        return declarationService.getDById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Declaration> getByUser(@PathVariable Long userId) {
        return declarationService.getU(userId);
    }

    @PostMapping
    public ResponseEntity<Declaration> create(@RequestBody Declaration d) {
        return ResponseEntity.status(201).body(declarationService.creatD(d));
    }

    @PutMapping("/{id}")
    public Declaration update(@PathVariable Long id, @RequestBody Declaration d) {
        return declarationService.editDById(id,d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        declarationService.DeleteB(id);
        return ResponseEntity.noContent().build();
    }
}