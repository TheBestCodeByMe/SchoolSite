package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pupil")
@ToString
class Pupil(userId: User, name: String, lastname: String, patronymic: String, dateOfBirthday: Date, email: String, personalCheck: String, classroomId: Classroom, parentsId: Parents) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private val userId: User

    @Column(name = "name", nullable = false)
    private val name: String

    @Column(name = "lastname", nullable = false)
    private val lastname: String

    @Column(name = "patronymic", nullable = false)
    private val patronymic: String

    @Column(name = "date_of_birthday")
    private val dateOfBirthday: Date

    @Column(name = "email")
    private val email: String

    @Column(name = "personal_check", nullable = false)
    private val personalCheck: String

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id", nullable = false)
    private val classroomId: Classroom

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "parents_id", nullable = false)
    private val parentsId: Parents

    @OneToOne(optional = false, mappedBy = "pupilID")
    private val attendance: Attendance? = null

    @OneToOne(optional = false, mappedBy = "pupilID")
    private val academicPerfomance: AcademicPerfomance? = null

    init {
        this.userId = userId
        this.name = name
        this.lastname = lastname
        this.patronymic = patronymic
        this.dateOfBirthday = dateOfBirthday
        this.email = email
        this.personalCheck = personalCheck
        this.classroomId = classroomId
        this.parentsId = parentsId
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val pupil = o as Pupil
        return Objects.equals(id, pupil.id) && Objects.equals(userId, pupil.userId) && Objects.equals(name, pupil.name) && Objects.equals(lastname, pupil.lastname) && Objects.equals(patronymic, pupil.patronymic) && Objects.equals(dateOfBirthday, pupil.dateOfBirthday) && Objects.equals(email, pupil.email) && Objects.equals(personalCheck, pupil.personalCheck) && Objects.equals(classroomId, pupil.classroomId) && Objects.equals(parentsId, pupil.parentsId)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, userId, name, lastname, patronymic, dateOfBirthday, email, personalCheck, classroomId, parentsId)
    }
}