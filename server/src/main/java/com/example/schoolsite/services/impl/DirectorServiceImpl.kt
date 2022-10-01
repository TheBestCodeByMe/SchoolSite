package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.ClassroomDTO

@Service
@RequiredArgsConstructor
class DirectorServiceImpl : DirectorService {
    private val teacherRepository: TeacherRepository? = null
    private val classroomRepository: ClassroomRepository? = null

    @get:Override
    val allClassroom: List<Any>
        get() {
            val teachers: List<Teacher> = teacherRepository.findAll()
            val classroom: List<Classroom> = classroomRepository.findAll()
            val classroomDTOList: List<ClassroomDTO> = ArrayList()
            for (value in classroom) {
                for (teacher in teachers) {
                    if (Objects.equals(value.getClassroomTeacherId().getId(), teacher.getId())) {
                        classroomDTOList.add(Mapper.mapClassroomToClassroomDTO(value, teacher))
                    }
                }
            }
            return classroomDTOList
        }
}