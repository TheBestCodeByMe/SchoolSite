package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User?, Long?> {
    fun existsUserByLogin(login: String?): Boolean
    fun findByLogin(login: String?): Optional<User?>?
    fun existsByLogin(login: String?): Boolean?
}