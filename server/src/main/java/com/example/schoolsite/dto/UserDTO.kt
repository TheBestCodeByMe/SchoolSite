package com.example.schoolsite.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import java.util.Date
import java.util.Objects

@Getter
@Setter
@NoArgsConstructor
@ToString
class UserDTO(private val name: String, private val lastname: String, private val patronymic: String, private val email: String, private val login: String, private val password: String, private val role: String, private val status: String, private val link: String) {
    private val id: Long? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val userDTO = o as UserDTO
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(lastname, userDTO.lastname) && Objects.equals(patronymic, userDTO.patronymic) && Objects.equals(email, userDTO.email) && Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password) && Objects.equals(role, userDTO.role) && Objects.equals(status, userDTO.status) && Objects.equals(link, userDTO.link)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, name, lastname, patronymic, email, login, password, role, status, link)
    }
}