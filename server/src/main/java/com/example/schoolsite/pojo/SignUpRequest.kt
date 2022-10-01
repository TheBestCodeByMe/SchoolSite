package com.example.schoolsite.pojo

import lombok.Getter
import lombok.Setter
import java.util.Set

@Getter
@Setter
class SignUpRequest {
    private val name: String? = null
    private val lastname: String? = null
    private val patronymic: String? = null
    private val email: String? = null
    private val login: String? = null
    private val password: String? = null
    private val role: Set<String>? = null
    private val status: String? = null
    private val link: String? = null
}