package com.tarik.content_clander.services;

import com.tarik.content_clander.model.Role;
import com.tarik.content_clander.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Role not found with id "+ id));
    }

    public Role creatRole(Role role){
        return roleRepository.save(role);
    }

    public Role editByRoleId(Role role, Long id){
        if (!roleRepository.existsById(id)){
            throw new RuntimeException("Role not found with id "+ id);
        }
        role.setId_R(id);
        return roleRepository.save(role);

    }
    public void deleteRole(Long id){
        if (!roleRepository.existsById(id)){
            throw new RuntimeException("Role not found with id "+ id);
        }
        roleRepository.deleteById(id);
    }
}
