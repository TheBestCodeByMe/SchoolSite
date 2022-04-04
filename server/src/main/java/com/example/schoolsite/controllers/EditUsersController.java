package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.ClassroomRepository;
import com.example.schoolsite.workWithDatabase.repo.ParentsRepository;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/editUsers")
public class EditUsersController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PupilRepository pupilRepository;
    @Autowired
    private ParentsRepository parentsRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    //@GetMapping("/editUsers")
    //public List<User> getAllUsers() {
    //    return userRepository.findAll();
    //}

    @PostMapping("/createPupilDTO")
    public Pupil createPupil(@Validated @RequestBody PupilDTO pupilDTO) {
        System.out.println(pupilDTO);
        System.out.println("6");
        Pupil pupil = Mapper.mapToPupil(pupilDTO);
        Parents parents = Mapper.mapToParents(pupilDTO);
        Classroom classroom = Mapper.mapToClassroom(pupilDTO);
        Classroom classroom1 = classroomRepository.findClassroomByName(classroom.getName());
        if (classroom1 != null) {
            System.out.println("1C");
            pupil.setClassroomId(classroom1.getId());

            Parents parent = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());
            System.out.println(parents);

            if (parent != null) {
                System.out.println("1");
                pupil.setParentsId(parent.getId());
            } else {
                parentsRepository.save(parents);
                System.out.println("2");
                Parents newParents = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());
                pupil.setParentsId(newParents.getId());
            }
            return pupilRepository.save(pupil);
        }

        System.out.println(pupil);
        Pupil pupil1 = new Pupil();
        return pupilRepository.save(pupil1);
    }

    @GetMapping("/showPupilDTO")
    public List<PupilDTO> getAllPupilDTO() {
        List<Pupil> pupils = pupilRepository.findAll();
        List<Parents> parents = parentsRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();

        List<PupilDTO> pupilDTOS = new ArrayList<>();

        for (Pupil pupil : pupils) {
            for (Parents parent : parents) {
                if (pupil.getParentsId() == parent.getId()) {
                    for (Classroom classroom : classrooms) {
                        if (pupil.getClassroomId() == classroom.getId()) {
                            Mapper.mapToPupilDTO(pupil, parent, classroom);
                        }
                    }
                }
            }
        }

        return pupilDTOS;//userRepository.findAll();
    }

}
