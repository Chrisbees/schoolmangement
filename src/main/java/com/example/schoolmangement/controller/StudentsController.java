package com.example.schoolmangement.controller;

import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.StudentsClass;
import com.example.schoolmangement.pages.Pages;
import com.example.schoolmangement.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequestMapping("/student")
@Controller
public class StudentsController {

    public static String uploadDir = System.getProperty("user.dir") +
            "/src/main/resources/static/productImages";
    public static Pages pages = new Pages();

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentsController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("allStudents")
    public String allStudents(Model model) {
        return student(model, 0);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("allStudents/{pages}")
    public String student(Model model, @PathVariable(value = "pages") int page) {
        Page<StudentsClass> pg = studentService.pages(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("allStudents", pg);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("totalItems", pg.getTotalElements());
        return "allStudents";
    }

    //get staff model
    @GetMapping("/addStudent")
    public String getAddStudent(Model model) {
        StudentsClass studentsClass = new StudentsClass();
        Parents parents = new Parents();
        model.addAttribute("addStudent", studentsClass);
        return "addStudents";
    }

    //adds staff info with image)
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("addStudent") StudentsClass user,
                             @RequestParam("productImage") MultipartFile file,
                             @RequestParam("imgNames") String imgNames) throws IOException {
        String studentImageUUID;
        if (user.getTotalFees() != 0) {
            user.setFeesBalance(user.getTotalFees() - user.addNumbers());
        } else {
            user.setTotalFees(0);
        }

        if (!file.isEmpty()) {
            studentImageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, studentImageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            studentImageUUID = "img/avatarImg.jpg";
        }
        user.setStudentPhotos(studentImageUUID);
        studentService.addStudent(user);
        return "redirect:/student/allStudents";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("studentInfo/id")
    public String studentInfo(Model model, @RequestParam("id") int id) {
        model.addAttribute("studentInfo", studentService.fetchUserById(id).get());
        return "studentInformation";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("studentInfo")
    public String studentInfo(@ModelAttribute("studentInfo") StudentsClass user) throws IOException {
        studentService.addStudent(user);
        return "studentInformation";
   }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/studentsInfo/update")
    public String updateStudent(Model model, @RequestParam("studentId") int id) {
        StudentsClass studentsClass = studentService.fetchUserById(id).get();
        model.addAttribute("addStudent", studentsClass);
        return "addStudents";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/studentsInfo/delete")
    public String deleteStudent(@RequestParam("studentId") int id) {
        studentService.delete(id);
        return "redirect:/student/allStudents";
    }

}
