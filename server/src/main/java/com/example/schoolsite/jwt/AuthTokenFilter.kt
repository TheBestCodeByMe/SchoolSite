package com.example.schoolsite.jwt

import com.example.schoolsite.services.impl.UserDetailsServiceImpl

@RequiredArgsConstructor
class AuthTokenFilter : OncePerRequestFilter() {
    private val jwtUtils: JwtUtils? = null
    private val userDetailsService: UserDetailsServiceImpl? = null
    @Override
    @Throws(ServletException::class, IOException::class)
    protected fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse?, filterChain: FilterChain) {
        try {
            val jwt = parseJwt(request)
            if (jwt != null && jwtUtils!!.validateJwtToken(jwt)) {
                val username: String = jwtUtils!!.getUserNameFromJwtToken(jwt)
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                authenticationToken.setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                SecurityContextHolder.getContext().setAuthentication(authenticationToken)
            }
        } catch (e: Exception) {
            System.err.println(e)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth: String = request.getHeader("Authorization")
        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            headerAuth.substring(7, headerAuth.length())
        } else null
    }
}