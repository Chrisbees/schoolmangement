package com.example.schoolmangement.repository;


import com.example.schoolmangement.model.StudentsClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDataRepo extends JpaRepository<StudentsClass, Integer> {
}
