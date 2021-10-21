package com.example.schoolmangement.repository;

import com.example.schoolmangement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DtoRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
