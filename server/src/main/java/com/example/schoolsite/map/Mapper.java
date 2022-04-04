package com.example.schoolsite.map;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static PupilDTO mapToPupilDTO(Pupil pupils, Parents parents, Classroom classrooms) {
        PupilDTO pupilDTOS = new PupilDTO();
        pupilDTOS.setName(pupils.getName());
        pupilDTOS.setLastname(pupils.getLastname());
        pupilDTOS.setPatronymic(pupils.getPatronymic());
        pupilDTOS.setEmail(pupils.getEmail());
        pupilDTOS.setDateOfBirthday(pupils.getDateOfBirthday());
        pupilDTOS.setPersonalCheck(pupils.getPersonalCheck());
        pupilDTOS.setClassName(classrooms.getName());
        pupilDTOS.setNameMom(parents.getNameMom());
        pupilDTOS.setNameDad(parents.getNameDad());
        pupilDTOS.setLastnameDad(parents.getLastnameDad());
        pupilDTOS.setPatronymicDad(parents.getPatronymicDad());
        pupilDTOS.setLastnameMom(parents.getLastnameMom());
        pupilDTOS.setPatronymicMom(parents.getPatronymicMom());

        return pupilDTOS;
    }

    public static Pupil mapToPupil(PupilDTO pupilDTOs) {
        Pupil pupil = new Pupil();

        pupil.setName(pupilDTOs.getName());
        pupil.setLastname(pupilDTOs.getLastname());
        pupil.setPatronymic(pupilDTOs.getPatronymic());
        pupil.setEmail(pupilDTOs.getEmail());
        pupil.setDateOfBirthday(pupilDTOs.getDateOfBirthday());
        pupil.setPersonalCheck(pupilDTOs.getPersonalCheck());

        return pupil;
    }

    public static Parents mapToParents(PupilDTO pupilDTOs) {
        Parents parent = new Parents();
        parent.setNameMom(pupilDTOs.getNameMom());
        parent.setNameDad(pupilDTOs.getNameDad());
        parent.setLastnameDad(pupilDTOs.getLastnameDad());
        parent.setPatronymicDad(pupilDTOs.getPatronymicDad());
        parent.setLastnameMom(pupilDTOs.getLastnameMom());
        parent.setPatronymicMom(pupilDTOs.getPatronymicMom());
        return parent;
    }

    public static Classroom mapToClassroom(PupilDTO pupilDTOs) {
        Classroom classroom = new Classroom();
        classroom.setName(pupilDTOs.getClassName());
        return classroom;
    }
}
