package com.example.schoolmangement.service;

import com.example.schoolmangement.model.StudentsClass;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface StudentService{
    StudentsClass addStudent(StudentsClass studentClass);
    List<StudentsClass> getAllStudents();
    Optional<StudentsClass> fetchUserById(int id);
    void delete(int id);
    Page<StudentsClass> pages(int pageNumber);
}
