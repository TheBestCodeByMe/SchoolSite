package com.example.schoolsite.services.impl

import com.example.schoolsite.entity.Teacher

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl : EmployeeService {
    private val teacherRepository: TeacherRepository? = null

    @get:Override
    val allTeacher: List<Any>
        get() = teacherRepository.findAll()

    @Override
    fun getTeacherByFIO(teacher: Teacher): List<Teacher> {
        val teacherList: List<Teacher> = ArrayList()
        teacherList.add(teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()))
        return teacherList
    }

    @Override
    fun getTeacherByUserId(userId: String?): Teacher {
        return teacherRepository.findByUserId(Long.parseLong(userId))
    }
}