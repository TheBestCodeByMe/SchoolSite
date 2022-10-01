package com.example.schoolsite.services

import com.example.schoolsite.dto.SheduleDTO

interface SheduleService {
    @Throws(ResourceNotFoundException::class)
    fun getScheduleByIdAndDate(userId: Long?, date: String?): List<SheduleDTO?>?
    fun getScheduleDTOByIdAndDate(userId: Long?, date: String?): List<SheduleDTO?>?
}