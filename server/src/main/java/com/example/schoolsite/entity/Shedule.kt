package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "shedule")
@ToString
class Shedule(classroomID: Classroom, subjectID: Subject, teacherID: Teacher, weekDay: Int, date: Date, calendarId: Calendar, hometask: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id", nullable = false)
    private val classroomID: Classroom

    @OneToOne(optional = false, cascade = CascadeType.ALL) // спросить, может множество расписаний с одним предметом
    @JoinColumn(name = "subject_id", nullable = false)
    private val subjectID: Subject

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false)
    private val teacherID: Teacher

    @Column(name = "week_day", nullable = false)
    private val weekDay: Int

    @Column(name = "date", nullable = false)
    private val date: Date

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id", nullable = false)
    private val calendarId: Calendar

    @Column(name = "hometask")
    private val hometask: String

    @OneToOne(optional = false, mappedBy = "lessonID")
    private val attendance: Attendance? = null

    @OneToOne(optional = false, mappedBy = "lessonID")
    private val academicPerfomance: AcademicPerfomance? = null

    init {
        this.classroomID = classroomID
        this.subjectID = subjectID
        this.teacherID = teacherID
        this.weekDay = weekDay
        this.date = date
        this.calendarId = calendarId
        this.hometask = hometask
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val shedule = o as Shedule
        return Objects.equals(id, shedule.id) && Objects.equals(classroomID, shedule.classroomID) && Objects.equals(subjectID, shedule.subjectID) && Objects.equals(teacherID, shedule.teacherID) && Objects.equals(weekDay, shedule.weekDay) && Objects.equals(date, shedule.date) && Objects.equals(calendarId, shedule.calendarId) && Objects.equals(hometask, shedule.hometask)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, classroomID, subjectID, teacherID, weekDay, date, calendarId, hometask)
    }
}