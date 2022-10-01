package com.example.schoolsite.controllers

import com.example.schoolsite.dto.DiaryDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
class DiaryController {
    private val diaryService: DiaryService? = null
    @PostMapping("/addAttendanceAndAcademicPerfomance")
    fun addAttendanceAndAcademicPerfomance(@RequestBody diaryDTO: DiaryDTO): ResponseEntity<*> {
        var diaryDTO: DiaryDTO = diaryDTO
        if (diaryDTO.isAttendance()) {
            if (!diaryService.getAcademicPerfomance(diaryDTO)) {
                diaryDTO = diaryService.addAttendance(diaryDTO)
            } else {
                diaryDTO.setNamePupil("Оценка у ученика уже выставлена, то есть он был в этот день")
            }
        } else if (diaryDTO.getClassName().equals("")) {
            if (!diaryService.getAttendance(diaryDTO)) {
                diaryDTO = diaryService.addAcademicPerfomance(diaryDTO)
            } else {
                diaryDTO.setNamePupil("Этого ученика не было в этот день")
            }
        } else {
            diaryDTO = diaryService.addSubject(diaryDTO)
        }
        return if (diaryDTO.getNamePupil().equals("ок")) {
            ResponseEntity.ok().body("Оценка выставлена")
        } else {
            ResponseEntity.badRequest().body(MessageResponse(diaryDTO.getNamePupil()))
        }
    }

    @PostMapping("/getByUserId")
    fun getDiaryByUser(@RequestBody userId: String?): List<DiaryDTO> {
        return diaryService.getDiaryDTOByUser(Long.parseLong(userId))
    }

    @PostMapping("/getNumbAttendance")
    fun getNumberAttendance(@RequestBody userId: String?): String {
        return String.valueOf(diaryService.getNumbAttendance(Long.parseLong(userId)))
    }

    @PostMapping("/getAverageGrade")
    fun getAvrgGrade(@RequestBody userId: String?): String {
        val avGrade: Double = diaryService.getAverageGrade(Long.parseLong(userId))
        return if (avGrade != 0.0) {
            String.valueOf(avGrade)
        } else {
            "У ученика ещё нет оценок"
        }
    }

    @GetMapping("/getAllAboutPupil/{classForSearch}")
    @Throws(ResourceNotFoundException::class)
    fun getUserById(@PathVariable(value = "classForSearch") classForSearch: String?): ResponseEntity<List<DiaryDTO>> {
        return ResponseEntity.ok().body(diaryService.getDiaryDTOByClass(classForSearch))
    }

    @GetMapping("/getSaveGrades/{userId}")
    @Throws(ResourceNotFoundException::class)
    fun getSaveDiary(@PathVariable(value = "userId") userId: Long?): ResponseEntity<String> {
        diaryService.saveGradesByUserId(userId)
        return ResponseEntity.ok().body("okeyy")
    }
}