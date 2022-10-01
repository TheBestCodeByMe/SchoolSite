package com.example.schoolsite.controllers

import com.example.schoolsite.dto.UserDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class UserController {
    private val userService: UserService? = null

    @get:GetMapping("/users")
    val allUsers: List<Any>
        get() = userService.getAllUsers()

    @GetMapping("/users/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getUserById(@PathVariable(value = "id") userId: Long?): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

    @PutMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateUser(@PathVariable(value = "id") userId: Long?,
                   @Validated @RequestBody userDetails: UserDTO?): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.updateUser(userId, userDetails))
    }
}