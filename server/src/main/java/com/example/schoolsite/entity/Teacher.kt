package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teachers")
@ToString
class Teacher(userId: User, name: String, lastName: String, patronymic: String, email: String, qualification: String, position: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private val userId: User

    @Column(name = "name", nullable = false)
    private val name: String

    @Column(name = "lastname", nullable = false)
    private val lastName: String

    @Column(name = "patronymic", nullable = false)
    private val patronymic: String

    @Column(name = "email")
    private val email: String

    @Column(name = "qualification", nullable = false)
    private val qualification: String

    @Column(name = "position", nullable = false)
    private val position: String

    @OneToOne(optional = false, mappedBy = "classroomTeacherId")
    private val classroom: Classroom? = null

    @OneToOne(optional = false, mappedBy = "teacherID")
    private val shedule: Shedule? = null

    init {
        this.userId = userId
        this.name = name
        this.lastName = lastName
        this.patronymic = patronymic
        this.email = email
        this.qualification = qualification
        this.position = position
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val teacher = o as Teacher
        return Objects.equals(id, teacher.id) && Objects.equals(userId, teacher.userId) && Objects.equals(name, teacher.name) && Objects.equals(lastName, teacher.lastName) && Objects.equals(patronymic, teacher.patronymic) && Objects.equals(email, teacher.email) && Objects.equals(qualification, teacher.qualification) && Objects.equals(position, teacher.position)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, userId, name, lastName, patronymic, email, qualification, position)
    }
}