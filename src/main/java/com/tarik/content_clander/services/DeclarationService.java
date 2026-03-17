package com.tarik.content_clander.services;

import com.tarik.content_clander.DTO.DeclarationDTO;
import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.DeclarationStatus;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.exeptions.ResourceNotFoundException;
import com.tarik.content_clander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        declaration.setDate_creation(LocalDate.now());
        declaration.setStatut(DeclarationStatus.DRAFT);
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

    public DeclarationStatus submitToPortnet(Long id){
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Declaration not found with id "+id));

        if (declaration.getStatut() != DeclarationStatus.DRAFT){
            throw new RuntimeException("Declaration must be in DRAFT status to submit");
        }
        declaration.setStatut(DeclarationStatus.SUBMITTED);
        declarationRepository.save(declaration);
        return declaration.getStatut();
    }

    public DeclarationStatus sendToDuan(Long id){
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Declaration not found with id "+id));

        if (declaration.getStatut() != DeclarationStatus.SUBMITTED){
            throw new RuntimeException("Declaration must be in SUBMITTED status to pend");
        }
        declaration.setStatut(DeclarationStatus.PENDING_VALIDATION);
        declarationRepository.save(declaration);
        return declaration.getStatut();
    }

    public DeclarationStatus updateStatus(Long id, DeclarationStatus status){
        Declaration declaration = declarationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Declaration not found with id "+id));

        if (declaration.getStatut() != DeclarationStatus.PENDING_VALIDATION){
            throw new RuntimeException("Declaration must be in PENDING_VALIDATION status to accept or reject it");
        }
        declaration.setStatut(status);
        declarationRepository.save(declaration);
        return declaration.getStatut();
    }
}
