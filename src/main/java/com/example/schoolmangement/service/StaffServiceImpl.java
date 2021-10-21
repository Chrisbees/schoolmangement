package com.example.schoolmangement.service;

//import com.example.schoolmangement.auth.ApplicationUserAdmin;
import com.example.schoolmangement.auth.ApplicationUser;
import com.example.schoolmangement.auth.ApplicationUserAdmin;
import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.StaffClass;
import com.example.schoolmangement.pages.Pages;
import com.example.schoolmangement.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class StaffServiceImpl implements StaffService{

    public static Pages pages = new Pages();

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    public StaffServiceImpl(){
    }

    @Override
    public List<StaffClass> getAllStaff() {
        return staffRepo.findAll();
    }


    @Override
    public StaffClass addStaff(StaffClass staffClass) throws Exception {
        Optional<StaffClass> checkEmail = staffRepo.findByEmail(staffClass.getEmail());
        if (checkEmail.isPresent()) {
            throw new Exception("User already exists");
        }
        return staffRepo.save(staffClass);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<StaffClass> staffClass = staffRepo.findByUsername(username);
////        return User.builder().username(staffClass.get().getUsername()).password(staffClass.get().getPassword())
////                .roles(staffClass.get().getRoles()).accountExpired(false).accountLocked(false).disabled(false)
////                .credentialsExpired(false).build();
//        return staffClass.map(ApplicationUserAdmin::new)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//         }

    @Override
    public Optional<StaffClass> fetchUserById(int id) {
        return staffRepo.findById(id);
    }

    @Override
    public void deleteStaff(int id) {
        staffRepo.deleteById(id);
    }

    @Override
    public Page<StaffClass> pages(int pageNumber) {
        Sort sort = Sort.by(pages.getSortDirection(), pages.getSortBy());
        Pageable staffPages = PageRequest.of(pageNumber, pages.getPageSize(), sort);
        return staffRepo.findAll(staffPages);
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<StaffClass> parentsClass = staffRepo.findByUsername(username);
//        return parentsClass.map(ApplicationUserAdmin:: new)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//    }
}
