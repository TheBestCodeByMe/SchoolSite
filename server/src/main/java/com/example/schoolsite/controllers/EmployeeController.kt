package com.example.schoolsite.controllers

import com.example.schoolsite.entity.Teacher

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
class EmployeeController {
    private val employeeService: EmployeeService? = null

    @get:GetMapping("/getAll")
    val allTeacher: List<Any>
        get() = employeeService.getAllTeacher()

    @PostMapping("/getByFIO")
    fun getTeacherByFIO(@Validated @RequestBody teacher: Teacher?): List<Teacher> {
        return employeeService.getTeacherByFIO(teacher)
    }

    @PostMapping("/getByUserId")
    fun getTeacherByUserId(@RequestBody userId: String?): Teacher {
        return employeeService.getTeacherByUserId(userId)
    }
}