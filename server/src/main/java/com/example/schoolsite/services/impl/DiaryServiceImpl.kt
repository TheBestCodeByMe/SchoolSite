package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.DiaryDTO

@Service
@RequiredArgsConstructor
class DiaryServiceImpl : DiaryService {
    private val attendanceRepository: AttendanceRepository? = null
    private val academicPerfomanceRepository: AcademicPerfomanceRepository? = null
    private val pupilRepository: PupilRepository? = null
    private val sheduleRepository: SheduleRepository? = null
    private val subjectRepository: SubjectRepository? = null
    private val classroomRepository: ClassroomRepository? = null
    @Override
    fun addAcademicPerfomance(diaryDTO: DiaryDTO): DiaryDTO {
        val academicPerfomance = AcademicPerfomance()
        val pupil: Pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil())
        val subject: Subject = subjectRepository.findBySubjectName(diaryDTO.getSubject())
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика")
            return diaryDTO
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета")
            return diaryDTO
        }
        val shedule: Shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId().getId(), subject.getId())
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате")
            return diaryDTO
        }
        academicPerfomance.setClassID(pupil.getClassroomId())
        academicPerfomance.setLessonID(sheduleRepository.findById(shedule.getId()).get())
        academicPerfomance.setPupilID(pupilRepository.findById(pupil.getId()).get())
        academicPerfomance.setGrade(Integer.parseInt(diaryDTO.getGrade()))
        academicPerfomanceRepository.save(academicPerfomance)
        diaryDTO.setNamePupil("ок")
        return diaryDTO
    }

    @Override
    fun addAttendance(diaryDTO: DiaryDTO): DiaryDTO {
        val attendance = Attendance()
        val pupil: Pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil())
        val subject: Subject = subjectRepository.findBySubjectName(diaryDTO.getSubject())
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика")
            return diaryDTO
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета")
            return diaryDTO
        }
        val shedule: Shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId().getId(), subject.getId())
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате")
            return diaryDTO
        }
        attendance.setPupilID(pupilRepository.findById(pupil.getId()).get())
        attendance.setClassID(pupil.getClassroomId())
        attendance.setLessonID(sheduleRepository.findById(shedule.getId()).get())
        attendanceRepository.save(attendance)
        diaryDTO.setNamePupil("ок")
        return diaryDTO
    }

    @Override
    fun addSubject(diaryDTO: DiaryDTO): DiaryDTO {
        val subject: Subject = subjectRepository.findBySubjectName(diaryDTO.getSubject())
        val classroom: Classroom = classroomRepository.findClassroomByName(diaryDTO.getClassName())
        if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в предмете")
            return diaryDTO
        } else if (classroom == null) {
            diaryDTO.setNamePupil("Ошибка в названии класса")
            return diaryDTO
        }
        val shedule: Shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), classroom.getId(), subject.getId())
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате предмета")
            return diaryDTO
        }
        shedule.setHometask(diaryDTO.getHomework())
        sheduleRepository.save(shedule)
        return diaryDTO
    }

    @Override
    fun getAttendance(diaryDTO: DiaryDTO): Boolean {
        val pupil: Pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil())
        val subject: Subject = subjectRepository.findBySubjectName(diaryDTO.getSubject())
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика")
            return false
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета")
            return false
        }
        val shedule: Shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId().getId(), subject.getId())
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате")
            return false
        }
        return attendanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId().getId(), shedule.getId(), pupil.getId())
    }

    @Override
    fun getAcademicPerfomance(diaryDTO: DiaryDTO): Boolean {
        System.out.println("в проверке$diaryDTO")
        val pupil: Pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil())
        val subject: Subject = subjectRepository.findBySubjectName(diaryDTO.getSubject())
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика")
            return false
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета")
            return false
        }
        val shedule: Shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId().getId(), subject.getId())
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате")
            return false
        }
        return academicPerfomanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId().getId(), shedule.getId(), pupil.getId())
    }

    @Override
    fun getDiaryDTOByUser(id: Long?): List<DiaryDTO> {
        val pupil: Pupil = pupilRepository.findByUserId(id)
        val shedules: List<Shedule> = sheduleRepository.findAllByClassroomID(pupil.getClassroomId().getId())
        val subjects: List<Subject> = subjectRepository.findAll()
        val attendances: List<Attendance> = attendanceRepository.findAllByPupilID(pupil.getId())
        val academicPerfomances: List<AcademicPerfomance> = academicPerfomanceRepository.findAllByPupilID(pupil.getId())
        val diaryDTOList: List<DiaryDTO> = ArrayList()
        var attendanceBoolean = false
        var grade: String? = ""
        for (shedule in shedules) {
            var diaryDTO = DiaryDTO()
            for (subject in subjects) {
                if (Objects.equals(shedule.getSubjectID().getId(), subject.getId())) {
                    if (academicPerfomances == null) {
                        grade = ""
                    } else {
                        for (academicPerfomance in academicPerfomances) {
                            if (Objects.equals(academicPerfomance.getLessonID().getId(), shedule.getId())) {
                                grade = String.valueOf(academicPerfomance.getGrade())
                            } else {
                                grade = ""
                            }
                        }
                    }
                    if (attendances != null) {
                        for (attendance in attendances) {
                            attendanceBoolean = Objects.equals(shedule.getId(), attendance.getLessonID().getId())
                        }
                    } else {
                        attendanceBoolean = false
                    }
                    diaryDTO = Mapper.mapToDiaryDTO(shedule, pupil, classroomRepository.getById(pupil.getClassroomId().getId()), attendanceBoolean, grade, subject)
                }
            }
            diaryDTOList.add(diaryDTO)
        }
        return diaryDTOList
    }

    @Override
    fun getNumbAttendance(id: Long?): Int {
        val pupil: Pupil = pupilRepository.findByUserId(id)
        val attendance: List<Attendance> = attendanceRepository.findAllByPupilID(pupil.getId())
        return attendance.size()
    }

    // TODO: упростить, как в scheduleController
    @Override
    fun getDiaryDTOByClass(classForSearch: String?): List<DiaryDTO> {
        val diaryDTOList: List<DiaryDTO> = ArrayList()
        var diaryDTO = DiaryDTO()
        val classroom: Classroom = classroomRepository.findClassroomByName(classForSearch)
        if (classroom == null) {
            diaryDTO.setNamePupil("Такого класса нет")
            diaryDTOList.add(diaryDTO)
            return diaryDTOList
        }
        val pupilList: List<Pupil> = pupilRepository.findAllByClassroomId(classroom.getId())
        val shedules: List<Shedule> = sheduleRepository.findAllByClassroomID(classroom.getId())
        val subjects: List<Subject> = subjectRepository.findAll()
        var attendanceBoolean = false
        var grade: String? = ""
        for (pupil in pupilList) {
            val attendances: List<Attendance> = attendanceRepository.findAllByPupilID(pupil.getId())
            val academicPerfomances: List<AcademicPerfomance> = academicPerfomanceRepository.findAllByPupilID(pupil.getId())
            for (shedule in shedules) {
                diaryDTO = DiaryDTO()
                for (subject in subjects) {
                    if (Objects.equals(shedule.getSubjectID().getId(), subject.getId())) {
                        if (academicPerfomances == null) {
                            grade = ""
                        } else {
                            for (academicPerfomance in academicPerfomances) {
                                if (Objects.equals(academicPerfomance.getLessonID().getId(), shedule.getId())) {
                                    grade = String.valueOf(academicPerfomance.getGrade())
                                } else {
                                    grade = ""
                                }
                            }
                        }
                        if (attendances != null) {
                            for (attendance in attendances) {
                                attendanceBoolean = Objects.equals(shedule.getId(), attendance.getLessonID().getId())
                            }
                        } else {
                            attendanceBoolean = false
                        }
                        diaryDTO = Mapper.mapToDiaryDTO(shedule, pupil, classroomRepository.getById(pupil.getClassroomId().getId()), attendanceBoolean, grade, subject)
                    }
                }
                diaryDTOList.add(diaryDTO)
            }
        }
        return diaryDTOList
    }

    @Override
    fun getAverageGrade(id: Long?): Double {
        val pupil: Pupil = pupilRepository.findByUserId(id)
        val academicPerfomanceList: List<AcademicPerfomance> = academicPerfomanceRepository.findAllByPupilID(pupil.getId())
        var sumGrade = 0.0
        for (academicPerfomance in academicPerfomanceList) {
            sumGrade += academicPerfomance.getGrade()
        }
        return if (sumGrade != 0.0) {
            sumGrade / academicPerfomanceList.size()
        } else {
            0
        }
    }

    @Override
    fun saveGradesByUserId(userId: Long?) {
        val diaryDTOList: List<DiaryDTO> = getDiaryDTOByUser(userId)
        try {
            val objectMapper = ObjectMapper()
            val objectWriter: ObjectWriter = objectMapper.writer(DefaultPrettyPrinter())
            objectWriter.writeValue(File("D:\\BSUIR\\6semestr\\CourseWork\\Programm\\SchoolSite\\server\\src\\main\\resources\\" + diaryDTOList[0].getLastnamePupil() + "diary.json"), diaryDTOList)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}