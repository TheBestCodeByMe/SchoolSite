package com.example.schoolsite.services;

import com.example.schoolsite.dto.DiaryDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.workWithDatabase.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AcademicPerfomanceRepository academicPerfomanceRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private SheduleRepository sheduleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public DiaryDTO addAcademicPerfomance(DiaryDTO diaryDTO) {
        AcademicPerfomance academicPerfomance = new AcademicPerfomance();
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return diaryDTO;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return diaryDTO;
        }

        academicPerfomance.setClassID(pupil.getClassroomId());
        academicPerfomance.setLessonID(shedule.getId());
        academicPerfomance.setPupilID(pupil.getId());
        academicPerfomance.setGrade(diaryDTO.getGrade());
        academicPerfomanceRepository.save(academicPerfomance);

        return diaryDTO;
    }

    public DiaryDTO addAttendance(DiaryDTO diaryDTO) {
        Attendance attendance = new Attendance();
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return diaryDTO;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return diaryDTO;
        }

        attendance.setPupilID(pupil.getId());
        attendance.setClassID(pupil.getClassroomId());
        attendance.setLessonID(shedule.getId());
        attendanceRepository.save(attendance);

        return diaryDTO;
    }

    public DiaryDTO addSubject(DiaryDTO diaryDTO) {
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());
        Classroom classroom = classroomRepository.findClassroomByName(diaryDTO.getClassName());

        if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в предмете");
            return diaryDTO;
        } else if (classroom == null) {
            diaryDTO.setNamePupil("Ошибка в названии класса");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), classroom.getId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате предмета");
            return diaryDTO;
        }

        shedule.setHometask(diaryDTO.getHomework());
        sheduleRepository.save(shedule);

        return diaryDTO;
    }

    public boolean getAttendance(DiaryDTO diaryDTO) {
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return false;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return false;
        }
        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return false;
        }
        return attendanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId(), shedule.getId(), pupil.getId());
    }

    public boolean getAcademicPerfomance(DiaryDTO diaryDTO) {
        System.out.println("в проверке"+diaryDTO);
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return false;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return false;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return false;
        }
        return academicPerfomanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId(), shedule.getId(), pupil.getId());
    }
}
