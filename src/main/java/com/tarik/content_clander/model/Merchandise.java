package com.tarik.content_clander.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marchandises")
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_M;

    private String designation;
    private float weight;
    private float value;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "declaration_id")
    private Declaration declaration;

    public Long getId_M() { return id_M; }
    public void setId_M(Long id_M) { this.id_M = id_M; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public float getWeight() { return weight; }
    public void setPoids(float poids) { this.weight = poids; }

    public float getValue() { return value; }
    public void setValeur(float valeur) { this.value = valeur; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Declaration getDeclaration() { return declaration; }
    public void setDeclaration(Declaration declaration) { this.declaration = declaration; }
}