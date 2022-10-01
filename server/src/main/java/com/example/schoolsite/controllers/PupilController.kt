package com.example.schoolsite.controllers

import com.example.schoolsite.dto.PupilDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/pupil")
@RequiredArgsConstructor
class PupilController {
    private val pupilService: PupilService? = null
    @PostMapping("/getByUserId")
    fun getPupilByFIO(@RequestBody userId: String?): PupilDTO {
        return pupilService.getPupilByFIO(userId)
    }
}