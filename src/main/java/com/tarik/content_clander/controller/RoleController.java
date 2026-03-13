package com.tarik.content_clander.controller;

import com.tarik.content_clander.model.Role;
import com.tarik.content_clander.repository.RoleRepository;
import com.tarik.content_clander.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/roles")
public class RoleController{

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role){
        return ResponseEntity.status(201).body(roleService.creatRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role role){
        return  ResponseEntity.ok(roleService.editByRoleId(role,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}