package com.example.schoolmangement.service;

import com.example.schoolmangement.model.StudentsClass;
import com.example.schoolmangement.pages.Pages;
import com.example.schoolmangement.repository.StudentDataRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    public static Pages pages = new Pages();
    private final StudentDataRepo studentDataRepo;

    @Autowired
    public StudentServiceImpl(StudentDataRepo studentDataRepo) {
        this.studentDataRepo = studentDataRepo;
    }

    @Override
    public StudentsClass addStudent(StudentsClass studentClass) {
        studentDataRepo.save(studentClass);
        return studentClass;
    }

    @Override
    public List<StudentsClass> getAllStudents() {
        return studentDataRepo.findAll();
    }

    @Override
    public Optional<StudentsClass> fetchUserById(int id) {
        return studentDataRepo.findById(id);
    }

    @Override
    public void delete(int id) {
        studentDataRepo.deleteById(id);
    }

    @Override
    public Page<StudentsClass> pages(int pageNumber) {
        Sort sort = Sort.by(pages.getSortDirection(), pages.getSortBy());
        Pageable studentPages = PageRequest.of(pageNumber, pages.getPageSize(), sort);
        return studentDataRepo.findAll(studentPages);
    }

}
