package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "classroom")
@ToString
class Classroom(classroomTeacherId: Teacher, name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_teacher_id", nullable = false)
    private val classroomTeacherId: Teacher

    @Column(name = "name", nullable = false)
    private val name: String

    @OneToOne(optional = false, mappedBy = "classroomId")
    private val pupil: Pupil? = null

    @OneToOne(optional = false, mappedBy = "classroomID")
    private val shedule: Shedule? = null

    @OneToOne(optional = false, mappedBy = "classID")
    private val attendance: Attendance? = null

    @OneToOne(optional = false, mappedBy = "classID")
    private val academicPerfomance: AcademicPerfomance? = null

    init {
        this.classroomTeacherId = classroomTeacherId
        this.name = name
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val classroom = o as Classroom
        return Objects.equals(id, classroom.id) && Objects.equals(classroomTeacherId, classroom.classroomTeacherId) && Objects.equals(name, classroom.name)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, classroomTeacherId, name)
    }
}