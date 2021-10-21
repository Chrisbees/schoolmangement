package com.example.schoolmangement.dto;

import com.example.schoolmangement.service.ParentServiceImpl;
import com.example.schoolmangement.service.StaffServiceImpl;
import lombok.Data;

@Data
public class ServiceDto {
    public StaffServiceImpl staffService;
    public ParentServiceImpl parentService;
}
