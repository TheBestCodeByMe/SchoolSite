package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.*;
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
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private SheduleRepository sheduleRepository;

    //@GetMapping("/editUsers")
    //public List<User> getAllUsers() {
    //    return userRepository.findAll();
    //}

    @PostMapping("/createPupilDTO")
    public Pupil createPupil(@Validated @RequestBody PupilDTO pupilDTO) {
        Pupil pupil = Mapper.mapPupilDTOToPupil(pupilDTO);
        Parents parents = Mapper.mapPupilDTOToParents(pupilDTO);
        Classroom classroom = Mapper.mapPupilDTOToClassroom(pupilDTO);
        Classroom classroom1 = classroomRepository.findClassroomByName(classroom.getName());

        if (classroom1 != null) {
            pupil.setClassroomId(classroom1.getId());

            Parents parent = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());

            if (parent != null) {
                pupil.setParentsId(parent.getId());
            } else {
                parentsRepository.save(parents);
                Parents newParents = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());
                pupil.setParentsId(newParents.getId());
            }
            return pupilRepository.save(pupil);
        }

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

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@Validated @RequestBody Teacher teacher) {
        if (teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()) == null) {
            teacherRepository.save(teacher);
            return teacher;
        }
        return null;
    }

    @PostMapping("/createSubject")
    public Subject createSubject(@Validated @RequestBody Subject subject) {
        if (subjectRepository.findBySubjectName(subject.getSubjectName()) == null) {
            subjectRepository.save(subject);
            return subject;
        }
        return null;
    }

    @PostMapping("/createSheduleDTO")
    public SheduleDTO createSheduleDTO(@Validated @RequestBody SheduleDTO sheduleDTO) {
        Classroom classroom = classroomRepository.findClassroomByName(sheduleDTO.getClassroomName());
        Subject subject = subjectRepository.findBySubjectName(sheduleDTO.getSubjectName());
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(sheduleDTO.getNameTeacher(), sheduleDTO.getLastnameTeacher(), sheduleDTO.getPatronymicTeacher());
        Calendar calendar = calendarRepository.findByLessonNumberAndWeekDay(sheduleDTO.getLessonNumber(), sheduleDTO.getWeekDay());

        if (classroom == null) {
            sheduleDTO.setSubjectName("Такого класса не существует");
        } else if (subject == null) {
            sheduleDTO.setSubjectName("Такого предмета не существует");
        } else if (teacher == null) {
            sheduleDTO.setSubjectName("Такого преподавателя не существует");
        } else if (calendar == null) {
            sheduleDTO.setSubjectName("Такого времени урока не существует");
        } else {
            Shedule shedule = Mapper.mapSheduleDTOToShedule(sheduleDTO, calendar.getId(), teacher.getId(), subject.getId(), classroom.getId());

            // TODO:
            // Прописать здесь, если уже класс в это время занят
            // Если уже учитель занят в это время
            // Если такое занятие уже есть
            // Тогда не сохранять
            //if () {
                sheduleRepository.save(shedule);
            //}
        }
        return null;
    }
}
