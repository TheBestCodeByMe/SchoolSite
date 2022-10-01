package com.example.schoolsite.services.impl

import com.example.schoolsite.entity.User

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl : UserDetailsService {
    private val userRepository: UserRepository? = null
    @Override
    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository
                .findByLogin(username)
                .orElseThrow { UsernameNotFoundException("User not found with  username: $username") }
        return UserDetailsImpl.build(user)
    }
}