package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parents")
@ToString
class Parents(@field:Column(name = "nameMom", nullable = false) private val nameMom: String, @field:Column(name = "lastnameMom", nullable = false) private val lastnameMom: String, @field:Column(name = "patronymicMom", nullable = false) private val patronymicMom: String, @field:Column(name = "nameDad", nullable = false) private val nameDad: String, @field:Column(name = "lastnameDad", nullable = false) private val lastnameDad: String, @field:Column(name = "patronymicDad", nullable = false) private val patronymicDad: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToMany(mappedBy = "parentsId", fetch = FetchType.EAGER)
    private val pupils: Pupil? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val parents = o as Parents
        return Objects.equals(id, parents.id) && Objects.equals(nameMom, parents.nameMom) && Objects.equals(lastnameMom, parents.lastnameMom) && Objects.equals(patronymicMom, parents.patronymicMom) && Objects.equals(nameDad, parents.nameDad) && Objects.equals(lastnameDad, parents.lastnameDad) && Objects.equals(patronymicDad, parents.patronymicDad)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad)
    }
}