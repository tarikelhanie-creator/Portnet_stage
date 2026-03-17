package com.tarik.content_clander.controller;

import com.tarik.content_clander.DTO.DeclarationDTO;
import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.DeclarationStatus;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.services.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/declarations")
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;

    @GetMapping
    public List<DeclarationDTO> getAll() { return declarationService.getAllD();}

    @GetMapping("/{id}")
    public DeclarationDTO getById(@PathVariable Long id) {
        return declarationService.getDById(id);
    }

    @GetMapping("/user/{userId}")
    public List<DeclarationDTO> getByUser(@PathVariable Long userId) {
        return declarationService.getU(userId);
    }

    @PostMapping
    public ResponseEntity<DeclarationDTO> create(@RequestBody Declaration d) {
        return ResponseEntity.status(201).body(declarationService.creatD(d));
    }

    @PutMapping("/{id}")
    public DeclarationDTO update(@PathVariable Long id, @RequestBody Declaration d) {
        return declarationService.editDById(id,d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        declarationService.DeleteB(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/submit")
    public ResponseEntity<DeclarationStatus> submitToportnet(@PathVariable Long id){
        return ResponseEntity.ok(declarationService.submitToPortnet(id));
    }

    @PutMapping("/{id}/send")
    public ResponseEntity<DeclarationStatus> sendToDuan(@PathVariable Long id){
        return ResponseEntity.ok(declarationService.sendToDuan(id));
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<DeclarationStatus> updateToduan(@PathVariable Long id, @RequestParam DeclarationStatus status ){
        return ResponseEntity.ok(declarationService.updateStatus(id,status));
    }

}