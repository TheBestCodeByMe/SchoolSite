package com.example.schoolsite.controllers

import com.example.schoolsite.dto.ClassroomDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class DirectorController {
    private val directorService: DirectorService? = null

    @get:GetMapping("/classroomDTO")
    val allClassroom: List<Any>
        get() = directorService.getAllClassroom()
}