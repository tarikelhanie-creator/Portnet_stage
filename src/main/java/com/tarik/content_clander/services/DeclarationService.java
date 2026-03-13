package com.tarik.content_clander.services;

import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class DeclarationService {
    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Declaration> getAllD(){
        return declarationRepository.findAll();
    }

    public Declaration getDById(Long id){
        return declarationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Declaration not found with id "+id));
    }

    public List<Declaration> getU(Long id){
        return declarationRepository.findAll().stream()
                .filter(d ->d.getUser().getId_U().equals(id))
                .toList();
    }

    public Declaration creatD(Declaration declaration){
        User fullUser = userRepository.findById(declaration.getUser().getId_U())
                .orElseThrow(() -> new RuntimeException("User not found with id " + declaration.getUser().getId_U()));

        declaration.setUser(fullUser);
        return declarationRepository.save(declaration);
    }

    public Declaration editDById(Long id, Declaration declaration){
        if (!declarationRepository.existsById(id)){
            throw new RuntimeException("Declaration not found with id "+id);
        }
        declaration.setId_D(id);
        return  declarationRepository.save(declaration);
    }

    public void DeleteB(Long id){
        if (!declarationRepository.existsById(id)){
            throw new RuntimeException("Declaration not found with id "+id);
        }
        declarationRepository.deleteById(id);
    }
}
