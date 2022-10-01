package com.example.schoolsite.jwt

import com.example.schoolsite.services.impl.UserDetailsImpl

@Component
class JwtUtils {
    @Value("\${app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwtExpirationMs}")
    private val jwtExpirationMs = 0
    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsImpl = authentication.getPrincipal() as UserDetailsImpl
        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(Date())
                .setExpiration(Date(Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun validateJwtToken(jwt: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt)
            return true
        } catch (e: MalformedJwtException) {
            System.err.println(e.getMessage())
        } catch (e: IllegalArgumentException) {
            System.err.println(e.getMessage())
        }
        return false
    }

    fun getUserNameFromJwtToken(jwt: String?): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject()
    }
}