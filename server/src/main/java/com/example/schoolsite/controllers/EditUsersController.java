package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.User;
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

    @PostMapping("/createPupil")
    public Pupil createUser(@Validated @RequestBody Pupil pupil, @Validated Parents parents) {
        System.out.println(pupil);
        System.out.println(parents);
        parentsRepository.save(parents);
        // TODO: сделать поиск родителей по id и приписать к ребенку, чтобы соединить с родителями
        return pupilRepository.save(pupil);
    }

    @GetMapping("/showPupilDTO")
    public List<PupilDTO> getAllPupilDTO() {
        List<Pupil> pupils = pupilRepository.findAll();
        List<Parents> parents = parentsRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();
        List<PupilDTO> pupilDTOS = new ArrayList<>();

        for (int i = 0; i < pupils.size(); i++) {
            pupilDTOS.get(i).setName(pupils.get(i).getName());
            pupilDTOS.get(i).setLastname(pupils.get(i).getLastname());
            pupilDTOS.get(i).setPatronymic(pupils.get(i).getPatronymic());
            pupilDTOS.get(i).setEmail(pupils.get(i).getEmail());
            pupilDTOS.get(i).setDateOfBirthday(pupils.get(i).getDateOfBirthday());
            pupilDTOS.get(i).setPersonalCheck(pupils.get(i).getPersonalCheck());
            pupilDTOS.get(i).setClassName(classrooms.get(i).getName());
            pupilDTOS.get(i).setNameMom(parents.get(i).getNameMom());
            pupilDTOS.get(i).setNameDad(parents.get(i).getNameDad());
            pupilDTOS.get(i).setLastnameDad(parents.get(i).getLastnameDad());
            pupilDTOS.get(i).setPatronymicDad(parents.get(i).getPatronymicDad());
            pupilDTOS.get(i).setLastnameMom(parents.get(i).getLastnameMom());
            pupilDTOS.get(i).setPatronymicMom(parents.get(i).getPatronymicMom());
        }

        return pupilDTOS;//userRepository.findAll();
    }

}
