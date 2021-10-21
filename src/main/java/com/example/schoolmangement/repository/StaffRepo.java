package com.example.schoolmangement.repository;


import com.example.schoolmangement.model.StaffClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepo extends JpaRepository<StaffClass, Integer> {
    Optional<StaffClass> findByUsername(String username);

    Optional<StaffClass> findByEmail(String email);
}
