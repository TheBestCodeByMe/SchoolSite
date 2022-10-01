package com.example.schoolsite.services

import com.example.schoolsite.pojo.JwtResponse

interface AuthorizationService {
    fun authUser(loginRequest: LoginRequest?): JwtResponse?
    fun registerUser(signupRequest: SignUpRequest?): String?
}