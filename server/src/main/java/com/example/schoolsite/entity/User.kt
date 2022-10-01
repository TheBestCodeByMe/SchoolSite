package com.example.schoolsite.entity

import lombok.Getter

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = "login")])
@ToString
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @Column(name = "login", nullable = false)
    private var login: String

    @Column(name = "password", nullable = false)
    private var password: String

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = JoinColumn(name = "user_id"), inverseJoinColumns = JoinColumn(name = "role_id"))
    private val roles: Set<Role> = HashSet()

    @Column(name = "status", nullable = false)
    private var status: String

    @Column(name = "link", nullable = true)
    private var link: String? = null

    @OneToOne(optional = false, mappedBy = "userId")
    private val teacher: Teacher? = null

    @OneToOne(optional = false, mappedBy = "userId")
    private val pupil: Pupil? = null

    constructor(login: String, password: String, status: String) : super() {
        this.login = login
        this.password = password
        this.status = status
    }

    constructor(login: String, password: String, status: String, link: String?) {
        this.login = login
        this.password = password
        this.status = status
        this.link = link
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val user = o as User
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(status, user.status) && Objects.equals(link, user.link)
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id, login, password, roles, status, link)
    }
}