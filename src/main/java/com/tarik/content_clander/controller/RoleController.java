package com.tarik.content_clander.controller;

import com.tarik.content_clander.DTO.RoleDTO;
import com.tarik.content_clander.model.Role;
import com.tarik.content_clander.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody Role role) {
        return ResponseEntity.status(201).body(roleService.createRole(role));
    }

    @PutMapping("/{id}")
    public RoleDTO update(@PathVariable Long id, @RequestBody Role role) {
        return roleService.editByRoleId(role, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}