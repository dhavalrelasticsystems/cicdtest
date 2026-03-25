package com.example.cicdtest.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "materials")
    private Set<Furniture> furniture = new HashSet<>();

    public Material() {}

    public Material(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Furniture> getFurniture() { return furniture; }
    public void setFurniture(Set<Furniture> furniture) { this.furniture = furniture; }
}
