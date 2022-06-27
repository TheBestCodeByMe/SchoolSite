package com.example.schoolsite.services;

import com.example.schoolsite.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    List<Teacher> getAllTeacher();

    List<Teacher> getTeacherByFIO(Teacher teacher);

    Teacher getTeacherByUserId(String userId);
}
