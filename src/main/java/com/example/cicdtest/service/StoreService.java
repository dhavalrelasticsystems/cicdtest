package com.example.cicdtest.service;

import com.example.cicdtest.model.Furniture;
import com.example.cicdtest.model.Material;
import com.example.cicdtest.model.Store;
import com.example.cicdtest.repository.FurnitureRepository;
import com.example.cicdtest.repository.MaterialRepository;
import com.example.cicdtest.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final FurnitureRepository furnitureRepository;
    private final MaterialRepository materialRepository;

    public StoreService(StoreRepository storeRepository, FurnitureRepository furnitureRepository, MaterialRepository materialRepository) {
        this.storeRepository = storeRepository;
        this.furnitureRepository = furnitureRepository;
        this.materialRepository = materialRepository;
    }

    public Store createStore(String name, String location) {
        Store store = new Store(name, location);
        return storeRepository.save(store);
    }

    @Transactional
    public Furniture addFurniture(Long storeId, String furnitureName, String furnitureType, Set<String> materialNames) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));

        Furniture furniture = new Furniture(furnitureName, furnitureType);
        furniture.setStore(store);

        Set<Material> materials = new HashSet<>();
        for (String materialName : materialNames) {
            Material material = materialRepository.findByName(materialName)
                    .orElseGet(() -> new Material(materialName));
            materials.add(material);
        }

        furniture.setMaterials(materials);
        Furniture saved = furnitureRepository.save(furniture);

        store.getFurnishings().add(saved);
        storeRepository.save(store);

        return saved;
    }
}
