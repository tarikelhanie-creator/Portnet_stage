package com.tarik.content_clander.services;

import com.tarik.content_clander.DTO.RoleDTO;
import com.tarik.content_clander.exeptions.ResourceNotFoundException;
import com.tarik.content_clander.model.Role;
import com.tarik.content_clander.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    private RoleDTO toDTO(Role role) {
        return new RoleDTO(
                role.getId_R(),
                role.getName(),
                role.getDesc()
        );
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        return toDTO(role);
    }

    public RoleDTO createRole(Role role) {
        return toDTO(roleRepository.save(role));
    }

    public RoleDTO editByRoleId(Role role, Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with id " + id);
        }
        role.setId_R(id);
        return toDTO(roleRepository.save(role));
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with id " + id);
        }
        roleRepository.deleteById(id);
    }
}