package com.tarik.content_clander.services;

import com.tarik.content_clander.DTO.MerchandiseDTO;
import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.Merchandise;
import com.tarik.content_clander.exeptions.ResourceNotFoundException;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.repository.MarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseService {
    @Autowired
    private MarchandiseRepository marchandiseRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    private MerchandiseDTO toDTO(Merchandise merchandise){
        return new MerchandiseDTO(
                merchandise.getId_M(),
                merchandise.getDesignation(),
                merchandise.getWeight(),
                merchandise.getValue(),
                merchandise.getQuantite()
        );
    }

    public List<MerchandiseDTO> getAllM(){
        return marchandiseRepository.findAll().stream().map(this::toDTO).toList();
    }

    public MerchandiseDTO creatMarchandise(Merchandise marchandise){
        Declaration fullD = declarationRepository.findById(marchandise.getDeclaration().getId_D())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + marchandise.getDeclaration().getId_D()));

        marchandise.setDeclaration(fullD);
        return toDTO(marchandiseRepository.save(marchandise));
    }

    public List<MerchandiseDTO> getD(Long id){
        List<MerchandiseDTO> merchandise = marchandiseRepository.findAll().stream()
                .filter(m -> m.getDeclaration().getId_D().equals(id)).map(this::toDTO).toList();
        return  merchandise;
    }

    public MerchandiseDTO getMById(Long id){
        Merchandise merchandise = marchandiseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Merchandise not found with id "+ id));
        return toDTO(merchandise);
    }

    public MerchandiseDTO editMById(Long id, Merchandise marchandise){
        if (!marchandiseRepository.existsById(id)){
            throw new ResourceNotFoundException("Merchandise not found with id "+ id);
        }
        marchandise.setId_M(id);
        return toDTO(marchandiseRepository.save(marchandise));
    }

    public void deleteM(Long id){
        if (!marchandiseRepository.existsById(id)){
            throw new ResourceNotFoundException("Merchandise not found with id "+ id);
        }
        marchandiseRepository.deleteById(id);
    }

}
