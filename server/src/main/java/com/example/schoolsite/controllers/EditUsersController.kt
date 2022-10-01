package com.example.schoolsite.controllers

import com.example.schoolsite.dto.ClassroomDTO

@RestController
@CrossOrigin(origins = "http://localhost:4200") // убрать)))
@RequestMapping("/api/v1/editUsers")
@RequiredArgsConstructor
class EditUsersController {
    // TODO: убрать возможность нескольких добавлений пользователя к ученику/учителю
    private val editUsersService: EditUsersService? = null
    @PostMapping("/createPupilDTO")
    fun createPupil(@Validated @RequestBody pupilDTO: PupilDTO?): Pupil {
        return editUsersService.createPupil(pupilDTO)
    }

    @get:GetMapping("/showPupilDTO")
    val allPupilDTO: List<Any>
        get() = editUsersService.getAllPupilDTO()

    @PostMapping("/createTeacher")
    fun createTeacher(@Validated @RequestBody teacher: Teacher?): Teacher {
        return editUsersService.createTeacher(teacher)
    }

    @PostMapping("/createSubject")
    fun createSubject(@Validated @RequestBody subject: Subject?): Subject {
        return editUsersService.createSubject(subject)
    }

    @PostMapping("/createSheduleDTO")
    fun createSheduleDTO(@Validated @RequestBody sheduleDTO: SheduleDTO?): SheduleDTO {
        return editUsersService.createSheduleDTO(sheduleDTO)
    }

    @PostMapping("/createClassroomDTO")
    fun createClassroom(@Validated @RequestBody classroomDTO: ClassroomDTO?): ClassroomDTO {
        return editUsersService.createClassroom(classroomDTO)
    }

    @DeleteMapping("/deleteUser/{login}")
    @Throws(ResourceNotFoundException::class)
    fun deleteUser(@PathVariable(value = "login") login: String?): Map<String, Boolean> {
        return editUsersService.deleteUser(login)
    }

    @PostMapping("/blockUser/{login}")
    @Throws(ResourceNotFoundException::class)
    fun blockUser(@PathVariable(value = "login") login: String?): Map<String, Boolean> {
        return editUsersService.blockUser(login)
    }

    @PostMapping("/unblockUser/{login}")
    fun unblockUser(@PathVariable(value = "login") login: String?): Map<String, Boolean> {
        return editUsersService.unblockUser(login)
    }
}