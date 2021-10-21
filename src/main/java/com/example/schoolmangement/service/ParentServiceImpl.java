package com.example.schoolmangement.service;

import com.example.schoolmangement.auth.ApplicationUser;
//import com.example.schoolmangement.auth.ApplicationUserAdmin;
import com.example.schoolmangement.dto.ParentsDto;
import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.StudentsClass;
import com.example.schoolmangement.repository.ParentsRepo;
import com.example.schoolmangement.repository.StudentDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService{

    private final ParentsRepo parentsRepo;
    private final StudentDataRepo studentDataRepo;

    @Autowired
    public ParentServiceImpl(ParentsRepo parentsRepo, StudentDataRepo studentDataRepo) {
        this.parentsRepo = parentsRepo;
        this.studentDataRepo = studentDataRepo;
    }

    @Override
    public Parents addParent(Parents parents) {
        return parentsRepo.save(parents);
    }

    @Override
    public StudentsClass addStudent(StudentsClass studentClass) {
        return studentDataRepo.save(studentClass);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Parents> parentsClass = parentsRepo.findByUsername(username);
//        return parentsClass.map(ApplicationUser:: new)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//    }

}
