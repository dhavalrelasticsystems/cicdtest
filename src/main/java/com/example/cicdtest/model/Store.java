package com.example.cicdtest.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Furniture> furnishings = new HashSet<>();

    public Store() {}

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Set<Furniture> getFurnishings() { return furnishings; }
    public void setFurnishings(Set<Furniture> furnishings) { this.furnishings = furnishings; }

    public void addFurniture(Furniture furniture) {
        furnishings.add(furniture);
        furniture.setStore(this);
    }
}
