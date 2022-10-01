package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Parents
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParentsRepository : JpaRepository<Parents?, Long?> {
    fun findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(nameDad: String?, lastnameDad: String?, patronymicDad: String?, nameMom: String?, lastnameMom: String?, patronymicMom: String?): Parents?
}