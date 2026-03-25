package com.example.cicdtest.repository;

import com.example.cicdtest.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
