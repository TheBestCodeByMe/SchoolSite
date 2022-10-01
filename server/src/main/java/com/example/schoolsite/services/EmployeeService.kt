package com.example.schoolsite.services

import com.example.schoolsite.entity.Teacher

interface EmployeeService {
    val allTeacher: List<Any?>?
    fun getTeacherByFIO(teacher: Teacher?): List<Teacher?>?
    fun getTeacherByUserId(userId: String?): Teacher?
}