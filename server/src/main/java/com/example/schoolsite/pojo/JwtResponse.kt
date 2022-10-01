package com.example.schoolsite.pojo

import com.example.schoolsite.entity.Role
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.List
import java.util.Set

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class JwtResponse(private val token: String, private val id: Long, private val login: String, private val roles: List<String>) {
    private val type = "Bearer"
}