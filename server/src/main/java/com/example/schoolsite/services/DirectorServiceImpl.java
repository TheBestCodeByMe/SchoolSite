package com.example.schoolsite.services;

import com.example.schoolsite.dto.ClassroomDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.ClassroomRepository;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final TeacherRepository teacherRepository;

    private final ClassroomRepository classroomRepository;

    @Override
    public List<ClassroomDTO> getAllClassroom() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Classroom> classroom = classroomRepository.findAll();
        List<ClassroomDTO> classroomDTOList = new ArrayList<>();

        for (Classroom value : classroom) {
            for (Teacher teacher : teachers) {
                if(Objects.equals(value.getClassroomTeacherId(), teacher.getId())){
                    classroomDTOList.add(Mapper.mapClassroomToClassroomDTO(value, teacher));}
            }
        }
        return classroomDTOList;
    }
}
