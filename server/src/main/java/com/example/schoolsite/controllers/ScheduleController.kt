package com.example.schoolsite.controllers

import com.example.schoolsite.dto.SheduleDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ScheduleController {
    private val sheduleService: SheduleService? = null
    @GetMapping("/getScheduleDTOPupil/{userId}/{date}")
    @Throws(ResourceNotFoundException::class)
    fun getScheduleByIdAndDate(@PathVariable(value = "userId") userId: Long?, @PathVariable(value = "date") date: String?): ResponseEntity<List<SheduleDTO>> {
        return ResponseEntity.ok().body(sheduleService.getScheduleByIdAndDate(userId, date))
    }
}