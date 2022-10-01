package com.example.schoolsite.services

import com.example.schoolsite.dto.UserDTO

interface UserService {
    val allUsers: List<Any?>?

    @Throws(ResourceNotFoundException::class)
    fun getUserById(userId: Long?): UserDTO?

    @Throws(ResourceNotFoundException::class)
    fun updateUser(userId: Long?,
                   userDetails: UserDTO?): UserDTO?
}