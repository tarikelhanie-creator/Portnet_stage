package com.tarik.content_clander.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_R;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId_R() { return id_R; }
    public void setId_R(Long id_R) { this.id_R = id_R; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesc() { return description; }
    public void setDesc(String desc) { this.description = desc; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}