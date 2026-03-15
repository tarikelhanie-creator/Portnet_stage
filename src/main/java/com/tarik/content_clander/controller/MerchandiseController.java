package com.tarik.content_clander.controller;

import com.tarik.content_clander.DTO.MerchandiseDTO;
import com.tarik.content_clander.model.Merchandise;
import com.tarik.content_clander.services.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/merchandises")
public class MerchandiseController {

    @Autowired
    private MerchandiseService marchandiseService;



    @GetMapping
    public List<MerchandiseDTO> getAll() { return marchandiseService.getAllM(); }

    @GetMapping("/{id}")
    public MerchandiseDTO getById(@PathVariable Long id) {
        return marchandiseService.getMById(id);
    }

    @GetMapping("/declaration/{declId}")
    public List<MerchandiseDTO> getByDeclaration(@PathVariable Long Id) {
        return marchandiseService.getD(Id);
    }

    @PostMapping
    public ResponseEntity<MerchandiseDTO> create(@RequestBody Merchandise m) {
        return ResponseEntity.status(201).body(marchandiseService.creatMarchandise(m));
    }

    @PutMapping("/{id}")
    public MerchandiseDTO update(@PathVariable Long id, @RequestBody Merchandise m) {
        return marchandiseService.editMById(id,m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marchandiseService.deleteM(id);
        return ResponseEntity.noContent().build();
    }
}