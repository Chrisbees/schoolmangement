package com.example.schoolmangement.dto;

import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.Role;
import com.example.schoolmangement.model.StaffClass;
import com.example.schoolmangement.model.StudentsClass;
import lombok.Data;

import java.util.List;

@Data
public class ParentsDto{
    public int id;
    public Parents parents;
    public StudentsClass student;
    public Role role;
    public StaffClass staffClass;
    }
