package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.ClassroomRepository;
import com.example.schoolsite.workWithDatabase.repo.ParentsRepository;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/pupil")
public class PupilController {
    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private ParentsRepository parentsRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @PostMapping("/getByUserId")
    public PupilDTO getPupilByFIO(@RequestBody String userId) {
        Pupil pupil = pupilRepository.findByUserId(Long.parseLong(userId));
        Parents parents = parentsRepository.getById(pupil.getParentsId());
        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());
         return Mapper.mapToPupilDTO(pupil, parents, classroom);
    }
}
