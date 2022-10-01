package com.example.schoolsite.services

import com.example.schoolsite.dto.PupilDTO

interface PupilService {
    fun getPupilByFIO(userId: String?): PupilDTO?
}