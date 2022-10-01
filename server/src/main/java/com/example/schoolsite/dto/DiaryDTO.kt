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
class DiaryDTO(private val namePupil: String, private val lastnamePupil: String, private val patronymicPupil: String, private val subject: String, private val homework: String, private val grade: String, private val attendance: Boolean, dateLesson: Date, className: String) {
    private val id: Long? = null
    private val dateLesson: Date
    private val className: String

    init {
        this.dateLesson = dateLesson
        this.className = className
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val diaryDTO = o as DiaryDTO
        return attendance == diaryDTO.attendance && Objects.equals(id, diaryDTO.id) && Objects.equals(namePupil, diaryDTO.namePupil) && Objects.equals(lastnamePupil, diaryDTO.lastnamePupil) && Objects.equals(patronymicPupil, diaryDTO.patronymicPupil) && Objects.equals(subject, diaryDTO.subject) && Objects.equals(homework, diaryDTO.homework) && Objects.equals(grade, diaryDTO.grade) && Objects.equals(dateLesson, diaryDTO.dateLesson) && Objects.equals(className, diaryDTO.className)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, namePupil, lastnamePupil, patronymicPupil, subject, homework, grade, attendance, dateLesson, className)
    }
}