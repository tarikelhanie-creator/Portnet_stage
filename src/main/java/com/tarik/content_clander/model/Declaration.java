package com.tarik.content_clander.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "declarations")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_D;

    private String number;
    private String type;
    private LocalDate date_creation;

    @Enumerated(EnumType.STRING)
    private DeclarationStatus statut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL)
    private List<Merchandise> marchandises;

    public Long getId_D() { return id_D; }
    public void setId_D(Long id_D) { this.id_D = id_D; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public void setStatut(DeclarationStatus statut) { this.statut = statut; }
    public DeclarationStatus getStatut() { return statut; }



    public LocalDate getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDate date_creation) { this.date_creation = date_creation; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Merchandise> getMarchandises() { return marchandises; }
    public void setMarchandises(List<Merchandise> marchandises) { this.marchandises = marchandises; }
}