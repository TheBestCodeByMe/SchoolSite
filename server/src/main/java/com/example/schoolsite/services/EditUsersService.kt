package com.example.schoolsite.services

import com.example.schoolsite.dto.ClassroomDTO

interface EditUsersService {
    fun createPupil(pupilDTO: PupilDTO?): Pupil?
    val allPupilDTO: List<Any?>?
    fun createTeacher(teacher: Teacher?): com.example.schoolsite.entity.Teacher?
    fun createSubject(
            subject: Subject?): Subject?

    fun createSheduleDTO(sheduleDTO: SheduleDTO?): SheduleDTO?
    fun createClassroom(classroomDTO: ClassroomDTO?): ClassroomDTO?

    @Throws(ResourceNotFoundException::class)
    fun deleteUser(login: String?): Map<String?, Boolean?>?

    @Throws(ResourceNotFoundException::class)
    fun blockUser(login: String?): Map<String?, Boolean?>?
    fun unblockUser(login: String?): Map<String?, Boolean?>?
}