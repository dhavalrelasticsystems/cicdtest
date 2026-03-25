package com.example.cicdtest.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "furniture_material",
            joinColumns = @JoinColumn(name = "furniture_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private Set<Material> materials = new HashSet<>();

    public Furniture() {}

    public Furniture(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    public Set<Material> getMaterials() { return materials; }
    public void setMaterials(Set<Material> materials) { this.materials = materials; }

    public void addMaterial(Material material) {
        materials.add(material);
        material.getFurniture().add(this);
    }
}
