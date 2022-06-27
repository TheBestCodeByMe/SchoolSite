package com.example.schoolsite.services;

import com.example.schoolsite.dto.ClassroomDTO;

import java.util.List;

public interface DirectorService {
    List<ClassroomDTO> getAllClassroom();
}
