package com.example.schoolmangement.repository;

import com.example.schoolmangement.model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentsRepo extends JpaRepository<Parents, Integer> {
    Optional<Parents> findByUsername(String username);
}
