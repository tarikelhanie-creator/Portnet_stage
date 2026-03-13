package com.tarik.content_clander.services;

import com.tarik.content_clander.model.Declaration;
import com.tarik.content_clander.model.Marchandise;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.repository.DeclarationRepository;
import com.tarik.content_clander.repository.MarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarchandiseService {
    @Autowired
    private MarchandiseRepository marchandiseRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    public List<Marchandise> getAllM(){
        return marchandiseRepository.findAll();
    }

    public Marchandise creatMarchandise(Marchandise marchandise){
        Declaration fullD = declarationRepository.findById(marchandise.getDeclaration().getId_D())
                .orElseThrow(() -> new RuntimeException("User not found with id " + marchandise.getDeclaration().getId_D()));

        marchandise.setDeclaration(fullD);
        return marchandiseRepository.save(marchandise);
    }

    public List<Marchandise> getD(Long id){
        return marchandiseRepository.findAll().stream()
                .filter(m -> m.getDeclaration().getId_D().equals(id))
                .toList();
    }

    public Marchandise getMById(Long id){
        return marchandiseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Merchandise not found with id "+ id));
    }

    public Marchandise editMById(Long id, Marchandise marchandise){
        if (!marchandiseRepository.existsById(id)){
            throw new RuntimeException("Merchandise not found with id "+ id);
        }
        marchandise.setId_M(id);
        return marchandiseRepository.save(marchandise);
    }

    public void deleteM(Long id){
        if (!marchandiseRepository.existsById(id)){
            throw new RuntimeException("Merchandise not found with id "+ id);
        }
        marchandiseRepository.deleteById(id);
    }

}
