package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calendar")
@ToString
class Calendar(@field:Column(name = "semesterID", nullable = false) private val semesterID: Int, @field:Column(name = "week_day", nullable = false) private val weekDay: Int, @field:Column(name = "lesson_number", nullable = false) private val lessonNumber: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, mappedBy = "calendarId")
    private val shedule: Shedule? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val calendar = o as Calendar
        return weekDay == calendar.weekDay && lessonNumber == calendar.lessonNumber && Objects.equals(id, calendar.id) && Objects.equals(semesterID, calendar.semesterID)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, semesterID, weekDay, lessonNumber)
    }
}