package com.example.schoolsite.services;

import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getTeacherByFIO(Teacher teacher) {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()));
        return teacherList;
    }

    @Override
    public Teacher getTeacherByUserId(String userId) {
        return teacherRepository.findByUserId(Long.parseLong(userId));
    }
}
