package com.example.schoolsite.jwt

import com.fasterxml.jackson.databind.ObjectMapper

// Если не авторизованный пользователь
@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {
    @Override
    @Throws(IOException::class, ServletException::class)
    fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        System.err.println(authException.getMessage())
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        val body: Map<String, Object> = HashMap()
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED)
        body.put("error", "Unauthorized")
        body.put("message", authException.getMessage())
        body.put("path", request.getServletPath())
        val mapper = ObjectMapper()
        mapper.writeValue(response.getOutputStream(), body)
    }
}