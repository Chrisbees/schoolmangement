package com.example.schoolmangement.service;

import com.example.schoolmangement.model.StaffClass;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<StaffClass> getAllStaff();
    StaffClass addStaff(StaffClass staffClass) throws Exception;
    Optional<StaffClass> fetchUserById(int id);
    void deleteStaff(int id);
    Page<StaffClass> pages(int pageNumber);
}
