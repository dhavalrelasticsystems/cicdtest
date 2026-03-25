package com.example.cicdtest.repository;

import com.example.cicdtest.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
}
