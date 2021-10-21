package com.example.schoolmangement.service;

import com.example.schoolmangement.dto.ParentsDto;
import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.StudentsClass;

public interface ParentService {
    Parents addParent(Parents parents);
    StudentsClass addStudent(StudentsClass studentClass);
}
