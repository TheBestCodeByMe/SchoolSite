package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "academicperfomance")
@ToString
class AcademicPerfomance(pupilID: Pupil, classID: Classroom, lessonID: Shedule, grade: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "pupil_id", nullable = false)
    private val pupilID: Pupil

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", nullable = false)
    private val classID: Classroom

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id", nullable = false)
    private val lessonID: Shedule

    @Column(name = "grade", nullable = false)
    private val grade: Int

    init {
        this.pupilID = pupilID
        this.classID = classID
        this.lessonID = lessonID
        this.grade = grade
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val that = o as AcademicPerfomance
        return grade == that.grade && Objects.equals(id, that.id) && Objects.equals(pupilID, that.pupilID) && Objects.equals(classID, that.classID) && Objects.equals(lessonID, that.lessonID)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, pupilID, classID, lessonID, grade)
    }
}