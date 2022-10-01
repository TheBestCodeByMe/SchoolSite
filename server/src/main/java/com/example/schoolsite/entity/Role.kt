package com.example.schoolsite.entity

import com.example.schoolsite.enumiration.ERole

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Role(rolePupil: ERole?) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private val name: ERole? = null

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val role = o as Role
        return Objects.equals(id, role.id) && name === role.name
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, name)
    }
}