package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.SheduleDTO

@Service
@RequiredArgsConstructor
class SheduleServiceImpl : SheduleService {
    private val attendanceRepository: AttendanceRepository? = null
    private val academicPerfomanceRepository: AcademicPerfomanceRepository? = null
    private val pupilRepository: PupilRepository? = null
    private val sheduleRepository: SheduleRepository? = null
    private val subjectRepository: SubjectRepository? = null
    private val classroomRepository: ClassroomRepository? = null
    private val teacherRepository: TeacherRepository? = null
    private val calendarRepository: CalendarRepository? = null
    @Override
    fun getScheduleByIdAndDate(@PathVariable(value = "userId") userId: Long?, @PathVariable(value = "date") date: String?): List<SheduleDTO> {
        return getScheduleDTOByIdAndDate(userId, date)
    }

    @Override
    fun getScheduleDTOByIdAndDate(userId: Long?, date: String?): List<SheduleDTO> {
        val pupil: Pupil = pupilRepository.findByUserId(userId)
        val sheduleList: List<Shedule> = sheduleRepository.findAllByClassroomIDAndDate(pupil.getClassroomId().getId(), Date.valueOf(date))
        val sheduleDTOList: List<SheduleDTO> = ArrayList()
        val classroom: Classroom = classroomRepository.getById(pupil.getClassroomId().getId())
        for (shedule in sheduleList) {
            val subject: Subject = subjectRepository.getById(shedule.getSubjectID().getId())
            val teacher: Teacher = teacherRepository.getById(shedule.getTeacherID().getId())
            val calendar: Calendar = calendarRepository.getById(shedule.getCalendarId().getId())
            val sheduleDTO: SheduleDTO = Mapper.mapSheduleToSheduleDTO(shedule, calendar, subject, teacher, classroom)
            sheduleDTOList.add(sheduleDTO)
        }
        return sheduleDTOList
    }
}