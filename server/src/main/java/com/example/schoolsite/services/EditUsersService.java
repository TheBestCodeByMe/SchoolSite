package com.example.schoolsite.services;

import com.example.schoolsite.dto.ClassroomDTO;
import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface EditUsersService {
    Pupil createPupil(PupilDTO pupilDTO);

    List<PupilDTO> getAllPupilDTO();

    com.example.schoolsite.entity.Teacher createTeacher(Teacher teacher);

    com.example.schoolsite.entity.Subject createSubject(
        com.example.schoolsite.entity.Subject subject);

    SheduleDTO createSheduleDTO(SheduleDTO sheduleDTO);

    ClassroomDTO createClassroom(ClassroomDTO classroomDTO);

    Map<String, Boolean> deleteUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> blockUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> unblockUser(String login);
}