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
class ClassroomDTO(private val name: String, private val classroomTeacherName: String, private val classroomTeacherLastname: String, private val classroomTeacherPatronymic: String) : Serializable {
    private val id: Long? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val that = o as ClassroomDTO
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(classroomTeacherName, that.classroomTeacherName) && Objects.equals(classroomTeacherLastname, that.classroomTeacherLastname) && Objects.equals(classroomTeacherPatronymic, that.classroomTeacherPatronymic)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, name, classroomTeacherName, classroomTeacherLastname, classroomTeacherPatronymic)
    }
}