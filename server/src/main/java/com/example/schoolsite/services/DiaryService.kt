package com.example.schoolsite.services

import com.example.schoolsite.dto.DiaryDTO

interface DiaryService {
    fun addAcademicPerfomance(diaryDTO: DiaryDTO?): DiaryDTO?
    fun addAttendance(diaryDTO: DiaryDTO?): DiaryDTO?
    fun addSubject(diaryDTO: DiaryDTO?): DiaryDTO?
    fun getAttendance(diaryDTO: DiaryDTO?): Boolean
    fun getAcademicPerfomance(diaryDTO: DiaryDTO?): Boolean
    fun getDiaryDTOByUser(id: Long?): List<DiaryDTO?>?
    fun getNumbAttendance(id: Long?): Int
    fun getDiaryDTOByClass(classForSearch: String?): List<DiaryDTO?>?
    fun getAverageGrade(id: Long?): Double
    fun saveGradesByUserId(userId: Long?)
}