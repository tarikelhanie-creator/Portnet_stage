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
    private String statut;
    private LocalDate date_creation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL)
    private List<Marchandise> marchandises;

    public Long getId_D() { return id_D; }
    public void setId_D(Long id_D) { this.id_D = id_D; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDate getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDate date_creation) { this.date_creation = date_creation; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Marchandise> getMarchandises() { return marchandises; }
    public void setMarchandises(List<Marchandise> marchandises) { this.marchandises = marchandises; }
}