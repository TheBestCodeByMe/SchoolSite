package com.example.schoolsite.controllers

import com.example.schoolsite.pojo.JwtResponse

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AutorizationController {
    private val authorizationService: AuthorizationService? = null
    @PostMapping("/signIn")
    fun authUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*> {
        val response: JwtResponse = authorizationService.authUser(loginRequest)
        return if (response != null) {
            ResponseEntity.ok(MessageResponse())
        } else {
            ResponseEntity
                    .badRequest()
                    .body(MessageResponse("Error: Your login or password are invalid"))
        }
    }

    @PostMapping("/signUp")
    fun registerUser(@RequestBody signupRequest: SignUpRequest?): ResponseEntity<*> {
        val status: String = authorizationService.registerUser(signupRequest)
        return if (status.contains("Error")) {
            ResponseEntity
                    .badRequest()
                    .body(MessageResponse(status))
        } else {
            ResponseEntity.ok(MessageResponse(status))
        }
    }
}