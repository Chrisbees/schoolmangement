package com.example.schoolmangement.controller;


import com.example.schoolmangement.dto.ParentsDto;
import com.example.schoolmangement.model.Role;
import com.example.schoolmangement.model.Users;
import com.example.schoolmangement.repository.DtoRepository;
import com.example.schoolmangement.repository.RolesRepository;
import com.example.schoolmangement.service.ParentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/parents")
public class ParentController {
    public static String uploadDir = System.getProperty("user.dir") +
            "/src/main/resources/static/productImages";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ParentServiceImpl parentService;
    private final DtoRepository dtoRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public ParentController(BCryptPasswordEncoder bCryptPasswordEncoder,
                            ParentServiceImpl parentService, DtoRepository dtoRepository, RolesRepository rolesRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.parentService = parentService;
        this.dtoRepository = dtoRepository;
        this.rolesRepository = rolesRepository;
    }


    @GetMapping("/register")
    public String getAddParent(Model model) {
        ParentsDto parentsDto = new ParentsDto();
//        parentsDto.getParents().getStudents().add(parentsDto.getStudent());
        model.addAttribute("addParent", parentsDto);
        return "addStudents";
    }

    @PostMapping("/register")
    public String addParent(@ModelAttribute("addParent") ParentsDto user,
                            @RequestParam("productImage") MultipartFile file,
                            @RequestParam("imgNames") String imgNames) throws IOException {
        String parentImageUUID;
        user.parents.setPassword(bCryptPasswordEncoder.encode(user.parents.getPassword()));
        if (!file.isEmpty()) {
            parentImageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, parentImageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            parentImageUUID = "img/avatarImg.jpg";
        }

        //Assign the role of user to users
        Role role = new Role();
        role.setName("USER");
        user.role.setName(role.getName());
        user.role.setParentsList(role.getParentsList());
        rolesRepository.save(role);
        user.parents.setRole(role);

        //Users Entity
        Users parent = new Users();
        List<Users> parentsList = new ArrayList<>();
        parent.setPassword(user.parents.getPassword());
        parent.setUsername(user.parents.getUsername());
        parent.setParents(parent.getParents());
        parentsList.add(parent);
        user.parents.setUsers(parentsList);
        parent.setUserRole(role);
        dtoRepository.save(parent);


        //Set user image
        user.parents.setParentPhotos(parentImageUUID);
        user.parents.setStudents(Arrays.asList(user.getStudent()));

        //Save parents
        parentService.addParent(user.getParents());
        return "redirect:/home/index";
    }

//    public StudentsClass studentsClass() {
//        StudentsClass studentsClass = new StudentsClass();
//        ParentsDto user = new ParentsDto();
//        List<StudentsClass> studentsClassList = new ArrayList<>();
//        studentsClassList.add(studentsClass);
//
//        return studentsClassList;
//
//
//    }

}
