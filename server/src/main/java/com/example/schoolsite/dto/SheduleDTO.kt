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
class SheduleDTO(private val classroomName: String, private val nameTeacher: String, private val lastnameTeacher: String, private val patronymicTeacher: String, private val subjectName: String, private val weekDay: Int, private val lessonNumber: Int, date: Date, semestrId: Int, hometask: String) : Serializable {
    private val id: Long? = null
    private val date: Date
    private val semestrId: Int
    private val hometask: String

    init {
        this.date = date
        this.semestrId = semestrId
        this.hometask = hometask
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val that = o as SheduleDTO
        return weekDay == that.weekDay && lessonNumber == that.lessonNumber && semestrId == that.semestrId && Objects.equals(id, that.id) && Objects.equals(classroomName, that.classroomName) && Objects.equals(nameTeacher, that.nameTeacher) && Objects.equals(lastnameTeacher, that.lastnameTeacher) && Objects.equals(patronymicTeacher, that.patronymicTeacher) && Objects.equals(subjectName, that.subjectName) && Objects.equals(date, that.date) && Objects.equals(hometask, that.hometask)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, classroomName, nameTeacher, lastnameTeacher, patronymicTeacher, subjectName, weekDay, lessonNumber, date, semestrId, hometask)
    }
}