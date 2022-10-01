package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Table(name = "questionFromUsers")
@Getter
@Setter
@NoArgsConstructor
@ToString
class Question(@field:Column(name = "question", nullable = false) private val question: String, @field:Column(name = "response") private val response: String, @field:Column(name = "flag") private val flag: Boolean) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val question1 = o as Question
        return flag == question1.flag && Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(response, question1.response)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, question, response, flag)
    }
}