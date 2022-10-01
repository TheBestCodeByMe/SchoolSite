package com.example.schoolsite.services.impl

import com.example.schoolsite.entity.User

class UserDetailsImpl(private val id: Long, @get:Override val username: String, @field:JsonIgnore @get:Override val password: String, authorities: Collection<GrantedAuthority?>, val status: String, val link: String) : UserDetails {

    private val authorities: Collection<GrantedAuthority?>

    init {
        this.authorities = authorities
    }

    @Override
    fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    fun getId(): Long? {
        return id
    }

    @get:Override
    val isAccountNonExpired: Boolean
        get() = true

    @get:Override
    val isAccountNonLocked: Boolean
        get() = true

    @get:Override
    val isCredentialsNonExpired: Boolean
        get() = true

    @get:Override
    val isEnabled: Boolean
        get() = true

    companion object {
        @Serial
        private val serialVersionUID = 1L
        fun build(user: User): UserDetailsImpl {
            val authorities: List<GrantedAuthority> = user.getRoles().stream()
                    .map { role -> SimpleGrantedAuthority(role.getName().name()) }
                    .collect(Collectors.toList())
            return UserDetailsImpl(
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    authorities,
                    user.getStatus(),
                    user.getLink())
        }
    }
}