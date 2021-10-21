package com.example.schoolmangement.repository;

import com.example.schoolmangement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
//     @Query("SELECT username FROM User WHERE username = :username")
//     User getUserByUsername(String username);

}
