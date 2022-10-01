package com.example.schoolsite.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import java.io.Serializable
import java.util.Date
import java.util.Objects

@Getter
@Setter
@NoArgsConstructor
@ToString
class PupilDTO(private val userId: Long, private val name: String, private val lastname: String, private val patronymic: String, dateOfBirthday: Date, email: String, personalCheck: String, nameMom: String, lastnameMom: String, patronymicMom: String, nameDad: String, lastnameDad: String, patronymicDad: String, className: String) : Serializable {
    private val id: Long? = null
    private val dateOfBirthday: Date
    private val email: String
    private val personalCheck: String
    private val nameMom: String
    private val lastnameMom: String
    private val patronymicMom: String
    private val nameDad: String
    private val lastnameDad: String
    private val patronymicDad: String
    private val className: String

    init {
        this.dateOfBirthday = dateOfBirthday
        this.email = email
        this.personalCheck = personalCheck
        this.nameMom = nameMom
        this.lastnameMom = lastnameMom
        this.patronymicMom = patronymicMom
        this.nameDad = nameDad
        this.lastnameDad = lastnameDad
        this.patronymicDad = patronymicDad
        this.className = className
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val pupilDTO = o as PupilDTO
        return Objects.equals(id, pupilDTO.id) && Objects.equals(userId, pupilDTO.userId) && Objects.equals(name, pupilDTO.name) && Objects.equals(lastname, pupilDTO.lastname) && Objects.equals(patronymic, pupilDTO.patronymic) && Objects.equals(dateOfBirthday, pupilDTO.dateOfBirthday) && Objects.equals(email, pupilDTO.email) && Objects.equals(personalCheck, pupilDTO.personalCheck) && Objects.equals(nameMom, pupilDTO.nameMom) && Objects.equals(lastnameMom, pupilDTO.lastnameMom) && Objects.equals(patronymicMom, pupilDTO.patronymicMom) && Objects.equals(nameDad, pupilDTO.nameDad) && Objects.equals(lastnameDad, pupilDTO.lastnameDad) && Objects.equals(patronymicDad, pupilDTO.patronymicDad) && Objects.equals(className, pupilDTO.className)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, userId, name, lastname, patronymic, dateOfBirthday, email, personalCheck, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad, className)
    }
}