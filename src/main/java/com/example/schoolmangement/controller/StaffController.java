package com.example.schoolmangement.controller;

import com.example.schoolmangement.model.Role;
import com.example.schoolmangement.model.StaffClass;
import com.example.schoolmangement.model.Users;
import com.example.schoolmangement.pages.Pages;
import com.example.schoolmangement.repository.DtoRepository;
import com.example.schoolmangement.repository.RolesRepository;
import com.example.schoolmangement.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/school")
@Controller
public class StaffController {

    public static String uploadDir = System.getProperty("user.dir") +
            "/src/main/resources/static/productImages";
    public static Pages pages = new Pages();
    private final StaffServiceImpl adminService;
    private final DtoRepository dtoRepository;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StaffController(StaffServiceImpl adminService, DtoRepository dtoRepository, RolesRepository rolesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminService = adminService;
        this.dtoRepository = dtoRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("allStaff")
    public String allStaff(Model model) {
            return staff(model, 0);
    }

    @GetMapping("allStaff/{pages}")
    public String staff(Model model, @PathVariable(value = "pages") int page) {
        Page<StaffClass> pg = adminService.pages(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("allStaff", pg);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("totalItems", pg.getTotalElements());
        return "allStaff";
    }

    @GetMapping("addStaff")
    public String getAddStaff(Model model) {
        StaffClass staffClass = new StaffClass();
        model.addAttribute("addStaff", staffClass);
        return "addStaff";
    }

    @PostMapping("addStaff")
    public String addStaff(@ModelAttribute("addStaff") StaffClass user,
                           @RequestParam("productImage") MultipartFile multipartFile,
                           @RequestParam("imgName") String imgName) throws Exception {
        String imageUUID;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (!multipartFile.isEmpty()) {
            imageUUID = multipartFile.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, multipartFile.getBytes());
        } else {
            imageUUID = "img/avatarImg.jpg";
        }
        List<Users> staffClassList = new ArrayList<>();
        Users staff = new Users();
        staff.setPassword(user.getPassword());
        staff.setUsername(user.getUsername());
        staffClassList.add(staff);
        user.setUsers(staffClassList);
        dtoRepository.save(staff);

        Role role = new Role();
        role.setName("ADMIN");
        staff.setUserRole(role);
        user.setRole(role);
        role.setStaffClasses(role.getStaffClasses());
        rolesRepository.save(role);

        user.setPhotos(imageUUID);
        adminService.addStaff(user);
        return "redirect:/school/allStaff";
    }

    @GetMapping("staffInfo/{id}")
    public String staffInfo(Model model, @PathVariable("id") int id) {
        model.addAttribute("staffInfo", adminService.fetchUserById(id).get());
        return "staffInformation";
    }

    @GetMapping("staffInfo/admin")
    public String makeAdmin(@RequestParam("id") int id) {
//        adminService.fetchUserById(id).get().setRoles(ADMIN.name());
//        staffClass.setRole(ADMIN.name());
        return "staffInformation";
    }

    @PostMapping("staffInfo")
    public String staffInfoPost(@ModelAttribute("staffInfo") StaffClass user) throws Exception {
        adminService.addStaff(user);
        return "staffInformation";
    }

    @GetMapping("/staffInfo/update")
    public String updateStaff(Model model, @RequestParam("staffId") int id) {
        StaffClass staffClass = adminService.fetchUserById(id).get();
        model.addAttribute("addStaff", staffClass);
        return "addStaff";
    }

    @GetMapping("/staffInfo/delete")
    public String deleteStaff(@RequestParam("staffId") int id) {
        adminService.deleteStaff(id);
        return "redirect:/school/allStaff";
    }

}


