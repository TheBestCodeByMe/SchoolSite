package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subjects")
@ToString
class Subject(@field:Column(name = "subject_name", nullable = false) private val subjectName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne(optional = false, mappedBy = "subjectID")
    private val shedule: Shedule? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val subject = o as Subject
        return Objects.equals(id, subject.id) && Objects.equals(subjectName, subject.subjectName)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, subjectName)
    }
}