3*package com.example.cicdtest.api;

import com.example.cicdtest.model.Furniture;
import com.example.cicdtest.model.Store;
import com.example.cicdtest.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreRequest request) {
        Store store = storeService.createStore(request.getName(), request.getLocation());
        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        return storeService.getSoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/furniture")
    public ResponseEntity<Furniture> addFurniture(@PathVariable Long id, @RequestBody FurnitureRequest request) {
        Furniture furniture = storeService.addFurniture(id, request.getName(), request.getType(), request.getMaterials());
        return ResponseEntity.ok(furniture);
    }

    public static class StoreRequest {
        private String name;
        private String location;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
    }

    public static class FurnitureRequest {
        private String name;
        private String type;
        private Set<String> materials;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Set<String> getMaterials() { return materials; }
        public void setMaterials(Set<String> materials) { this.materials = materials; }
    }
}
