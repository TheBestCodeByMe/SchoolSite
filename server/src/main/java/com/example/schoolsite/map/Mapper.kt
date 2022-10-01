package com.example.schoolsite.map

import com.example.schoolsite.dto.*
import com.example.schoolsite.entity.*
import com.example.schoolsite.enumiration.ERole
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set

object Mapper {
    fun mapToPupilDTO(pupils: Pupil, parents: Parents, classrooms: Classroom): PupilDTO {
        val pupilDTOS = PupilDTO()
        pupilDTOS.setName(pupils.getName())
        pupilDTOS.setLastname(pupils.getLastname())
        pupilDTOS.setPatronymic(pupils.getPatronymic())
        pupilDTOS.setEmail(pupils.getEmail())
        pupilDTOS.setDateOfBirthday(pupils.getDateOfBirthday())
        pupilDTOS.setPersonalCheck(pupils.getPersonalCheck())
        pupilDTOS.setClassName(classrooms.getName())
        pupilDTOS.setNameMom(parents.getNameMom())
        pupilDTOS.setNameDad(parents.getNameDad())
        pupilDTOS.setLastnameDad(parents.getLastnameDad())
        pupilDTOS.setPatronymicDad(parents.getPatronymicDad())
        pupilDTOS.setLastnameMom(parents.getLastnameMom())
        pupilDTOS.setPatronymicMom(parents.getPatronymicMom())
        return pupilDTOS
    }

    fun mapPupilDTOToPupil(pupilDTOs: PupilDTO): Pupil {
        val pupil = Pupil()
        pupil.setName(pupilDTOs.getName())
        pupil.setLastname(pupilDTOs.getLastname())
        pupil.setPatronymic(pupilDTOs.getPatronymic())
        pupil.setEmail(pupilDTOs.getEmail())
        pupil.setDateOfBirthday(pupilDTOs.getDateOfBirthday())
        pupil.setPersonalCheck(pupilDTOs.getPersonalCheck())
        return pupil
    }

    fun mapPupilDTOToParents(pupilDTOs: PupilDTO): Parents {
        val parent = Parents()
        parent.setNameMom(pupilDTOs.getNameMom())
        parent.setNameDad(pupilDTOs.getNameDad())
        parent.setLastnameDad(pupilDTOs.getLastnameDad())
        parent.setPatronymicDad(pupilDTOs.getPatronymicDad())
        parent.setLastnameMom(pupilDTOs.getLastnameMom())
        parent.setPatronymicMom(pupilDTOs.getPatronymicMom())
        return parent
    }

    fun mapPupilDTOToClassroom(pupilDTOs: PupilDTO): Classroom {
        val classroom = Classroom()
        classroom.setName(pupilDTOs.getClassName())
        return classroom
    }

    fun mapUserDTOToUser(userDTO: UserDTO): User {
        val user = User()
        user.setLogin(userDTO.getLogin())
        // TODO: переделать
        val role: Set<Role> = HashSet()
        role.add(Role(ERole.ROLE_PUPIL))
        user.setRoles(role)
        user.setPassword(userDTO.getPassword())
        user.setStatus(userDTO.getStatus())
        user.setLink(userDTO.getLink())
        return user
    }

    fun mapUserDTOToPupil(userDTO: UserDTO): Pupil {
        val pupil = Pupil()
        pupil.setEmail(userDTO.getEmail())
        pupil.setName(userDTO.getName())
        pupil.setLastname(userDTO.getLastname())
        pupil.setPatronymic(userDTO.getPatronymic())
        return pupil
    }

    fun mapUserToUserDTO(user: User, pupil: Pupil): UserDTO {
        val userDTO = UserDTO()
        userDTO.setLogin(user.getLogin())
        userDTO.setRole(user.getRoles().toString())
        userDTO.setPassword(user.getPassword())
        userDTO.setStatus(user.getStatus())
        userDTO.setLink(user.getLink())
        userDTO.setEmail(pupil.getEmail())
        userDTO.setLastname(pupil.getLastname())
        userDTO.setName(pupil.getName())
        userDTO.setPatronymic(pupil.getPatronymic())
        return userDTO
    }

    fun mapUserTeacherToUserDTO(user: User, teacher: Teacher): UserDTO {
        val userDTO = UserDTO()
        userDTO.setLogin(user.getLogin())
        userDTO.setRole(user.getRoles().toString())
        userDTO.setPassword(user.getPassword())
        userDTO.setStatus(user.getStatus())
        userDTO.setLink(user.getLink())
        userDTO.setEmail(teacher.getEmail())
        userDTO.setLastname(teacher.getLastName())
        userDTO.setName(teacher.getName())
        userDTO.setPatronymic(teacher.getPatronymic())
        return userDTO
    }

    fun mapSheduleToSheduleDTO(shedule: Shedule, calendar: Calendar, subject: Subject, teacher: Teacher, classroom: Classroom): SheduleDTO {
        val sheduleDTO = SheduleDTO()
        sheduleDTO.setDate(shedule.getDate())
        sheduleDTO.setHometask(shedule.getHometask())
        sheduleDTO.setClassroomName(classroom.getName())
        sheduleDTO.setLastnameTeacher(teacher.getLastName())
        sheduleDTO.setNameTeacher(teacher.getName())
        sheduleDTO.setPatronymicTeacher(teacher.getPatronymic())
        sheduleDTO.setLessonNumber(calendar.getLessonNumber())
        sheduleDTO.setSemestrId(calendar.getSemesterID())
        sheduleDTO.setSubjectName(subject.getSubjectName())
        return sheduleDTO
    }

    fun mapSheduleDTOToShedule(sheduleDTO: SheduleDTO, calendarId: Calendar?, teacherId: Teacher?, subjectId: Subject?, classroomId: Classroom?): Shedule {
        val shedule = Shedule()
        shedule.setDate(sheduleDTO.getDate())
        shedule.setHometask(sheduleDTO.getHometask())
        shedule.setWeekDay(sheduleDTO.getWeekDay())
        shedule.setCalendarId(calendarId)
        shedule.setSubjectID(subjectId)
        shedule.setTeacherID(teacherId)
        shedule.setClassroomID(classroomId)
        return shedule
    }

    fun mapSheduleDTOToCalendar(sheduleDTO: SheduleDTO): Calendar {
        val calendar = Calendar()
        calendar.setLessonNumber(sheduleDTO.getLessonNumber())
        calendar.setWeekDay(sheduleDTO.getWeekDay())
        calendar.setSemesterID(sheduleDTO.getSemestrId())
        return calendar
    }

    fun mapClassroomDTOToClassroom(classroomDTO: ClassroomDTO, teacherId: Teacher?): Classroom {
        val classroom = Classroom()
        classroom.setName(classroomDTO.getName())
        classroom.setClassroomTeacherId(teacherId)
        return classroom
    }

    fun mapClassroomToClassroomDTO(classroom: Classroom, teacher: Teacher): ClassroomDTO {
        val classroomDTO = ClassroomDTO()
        classroomDTO.setName(classroom.getName())
        classroomDTO.setClassroomTeacherName(teacher.getName())
        classroomDTO.setClassroomTeacherLastname(teacher.getLastName())
        classroomDTO.setClassroomTeacherPatronymic(teacher.getPatronymic())
        return classroomDTO
    }

    fun mapToDiaryDTO(shedule: Shedule, pupil: Pupil, classroom: Classroom, attendance: Boolean, grade: String?, subject: Subject): DiaryDTO {
        val diaryDTO = DiaryDTO()
        diaryDTO.setNamePupil(pupil.getName())
        diaryDTO.setLastnamePupil(pupil.getLastname())
        diaryDTO.setPatronymicPupil(pupil.getPatronymic())
        diaryDTO.setSubject(subject.getSubjectName())
        diaryDTO.setHomework(shedule.getHometask())
        diaryDTO.setGrade(grade)
        diaryDTO.setAttendance(attendance)
        diaryDTO.setDateLesson(shedule.getDate())
        diaryDTO.setClassName(classroom.getName())
        return diaryDTO
    }
}