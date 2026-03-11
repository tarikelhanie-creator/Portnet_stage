package com.tarik.content_clander.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_U;

    private String name;
    private String email;
    private String number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Declaration> declarations;

    public Long getId_U() { return id_U; }
    public void setId_U(Long id_U) { this.id_U = id_U; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Declaration> getDeclarations() { return declarations; }
    public void setDeclarations(List<Declaration> declarations) { this.declarations = declarations; }
}