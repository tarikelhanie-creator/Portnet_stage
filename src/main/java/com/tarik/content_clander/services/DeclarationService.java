package com.tarik.content_clander.services;

import com.tarik.content_clander.DTO.DeclarationDTO;
import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.exeptions.ResourceNotFoundException;
import com.tarik.content_clander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarationService {
    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    UserRepository userRepository;

    private DeclarationDTO toDTO(Declaration declaration){
        return new DeclarationDTO(
                declaration.getId_D(),
                declaration.getNumber(),
                declaration.getType(),
                declaration.getStatut(),
                declaration.getDate_creation()
        );
    }

    public List<DeclarationDTO> getAllD(){
        return declarationRepository.findAll().stream().map(this::toDTO).toList();
    }

    public DeclarationDTO getDById(Long id){
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Declaration not found with id "+id));
        return toDTO(declaration);
    }

    public List<DeclarationDTO> getU(Long id){
        return declarationRepository.findAll().stream()
                .filter(d ->d.getUser().getId_U().equals(id))
                .map(this::toDTO)
                .toList();
    }

    public DeclarationDTO creatD(Declaration declaration){
        User fullUser = userRepository.findById(declaration.getUser().getId_U())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + declaration.getUser().getId_U()));

        declaration.setUser(fullUser);
        return toDTO(declarationRepository.save(declaration));
    }

    public DeclarationDTO editDById(Long id, Declaration declaration){
        if (!declarationRepository.existsById(id)){
            throw new ResourceNotFoundException("Declaration not found with id "+id);
        }
        declaration.setId_D(id);
        return toDTO(declarationRepository.save(declaration));
    }

    public void DeleteB(Long id){
        if (!declarationRepository.existsById(id)){
            throw new ResourceNotFoundException("Declaration not found with id "+id);
        }
        declarationRepository.deleteById(id);
    }
}
