package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.ClassroomDTO

@Service
@RequiredArgsConstructor
class EditUsersServiceImpl : EditUsersService {
    private val userRepository: UserRepository? = null
    private val pupilRepository: PupilRepository? = null
    private val parentsRepository: ParentsRepository? = null
    private val classroomRepository: ClassroomRepository? = null
    private val teacherRepository: TeacherRepository? = null
    private val subjectRepository: SubjectRepository? = null
    private val calendarRepository: CalendarRepository? = null
    private val sheduleRepository: SheduleRepository? = null
    @Override
    fun createPupil(pupilDTO: PupilDTO?): Pupil {
        val pupil: Pupil = Mapper.mapPupilDTOToPupil(pupilDTO)
        val parents: Parents = Mapper.mapPupilDTOToParents(pupilDTO)
        val classroom: Classroom = Mapper.mapPupilDTOToClassroom(pupilDTO)
        val classroom1: Classroom = classroomRepository.findClassroomByName(classroom.getName())
        if (classroom1 != null) {
            pupil.setClassroomId(classroomRepository.getById(classroom1.getId()))
            val parent: Parents = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom())
            if (parent != null) {
                pupil.setParentsId(parentsRepository.getById(parent.getId()))
            } else {
                parentsRepository.save(parents)
                val newParents: Parents = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom())
                pupil.setParentsId(parentsRepository.getById(newParents.getId()))
            }
            return pupilRepository.save(pupil)
        }
        val pupil1 = Pupil()
        return pupilRepository.save(pupil1)
    }

    //userRepository.findAll();
    @get:Override
    val allPupilDTO: List<Any>
        get() {
            val pupils: List<Pupil> = pupilRepository.findAll()
            val parents: List<Parents> = parentsRepository.findAll()
            val classrooms: List<Classroom> = classroomRepository.findAll()
            val pupilDTOS: List<PupilDTO> = ArrayList()
            for (pupil in pupils) {
                for (parent in parents) {
                    if (pupil.getParentsId().getId() === parent.getId()) {
                        for (classroom in classrooms) {
                            if (pupil.getClassroomId().getId() === classroom.getId()) {
                                Mapper.mapToPupilDTO(pupil, parent, classroom)
                            }
                        }
                    }
                }
            }
            return pupilDTOS //userRepository.findAll();
        }

    @Override
    fun createTeacher(teacher: Teacher): Teacher? {
        if (teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()) == null) {
            teacherRepository.save(teacher)
            return teacher
        }
        return null
    }

    @Override
    fun createSubject(subject: Subject): Subject {
        if (subjectRepository.findBySubjectName(subject.getSubjectName()) == null) {
            subjectRepository.save(subject)
            return subject
        }
        subject.setSubjectName("Такой предмет уже есть")
        return subject
    }

    @Override
    fun createSheduleDTO(sheduleDTO: SheduleDTO): SheduleDTO {
        val classroom: Classroom = classroomRepository.findClassroomByName(sheduleDTO.getClassroomName())
        val subject: Subject = subjectRepository.findBySubjectName(sheduleDTO.getSubjectName())
        val teacher: Teacher = teacherRepository.findByNameAndLastNameAndPatronymic(sheduleDTO.getNameTeacher(), sheduleDTO.getLastnameTeacher(), sheduleDTO.getPatronymicTeacher())
        val calendar: Calendar = calendarRepository.findByLessonNumberAndWeekDay(sheduleDTO.getLessonNumber(), sheduleDTO.getWeekDay())
        if (classroom == null) {
            sheduleDTO.setSubjectName("Такого класса не существует")
        } else if (subject == null) {
            sheduleDTO.setSubjectName("Такого предмета не существует")
        } else if (teacher == null) {
            sheduleDTO.setSubjectName("Такого преподавателя не существует")
        } else if (calendar == null) {
            sheduleDTO.setSubjectName("Такого времени урока не существует")
        } else {
            val shedule: Shedule = Mapper.mapSheduleDTOToShedule(sheduleDTO, calendarRepository.getById(calendar.getId()), teacherRepository.getById(teacher.getId()), subjectRepository.getById(subject.getId()), classroomRepository.getById(classroom.getId()))
            if (sheduleRepository.findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(shedule.getCalendarId().getId(), shedule.getClassroomID().getId(), shedule.getDate(), shedule.getSubjectID().getId(), shedule.getTeacherID().getId(), shedule.getWeekDay()) != null) {
                sheduleDTO.setSubjectName("Такое расписание уже есть")
            } else if (sheduleRepository.findByTeacherIDAndCalendarIdAndDate(shedule.getTeacherID().getId(), shedule.getCalendarId().getId(), shedule.getDate()) != null) {
                sheduleDTO.setSubjectName("Учитель занят в это время")
            } else if (sheduleRepository.findByCalendarIdAndClassroomIDAndDate(shedule.getCalendarId().getId(), shedule.getClassroomID().getId(), shedule.getDate()) != null) {
                sheduleDTO.setSubjectName("У класса уже есть занятие в это время")
            } else {
                sheduleRepository.save(shedule)
            }
        }
        return sheduleDTO
    }

    @Override
    fun createClassroom(classroomDTO: ClassroomDTO): ClassroomDTO {
        val teacher: Teacher = teacherRepository.findByNameAndLastNameAndPatronymic(classroomDTO.getClassroomTeacherName(), classroomDTO.getClassroomTeacherLastname(), classroomDTO.getClassroomTeacherPatronymic())
        if (classroomRepository.findClassroomByName(classroomDTO.getName()) != null) {
            classroomDTO.setName("Такой класс уже есть")
        } else if (teacher == null) {
            classroomDTO.setName("Такого преподавателя нет")
        } else {
            val classroom: Classroom = Mapper.mapClassroomDTOToClassroom(classroomDTO, teacherRepository.getById(teacher.getId()))
            classroomRepository.save(classroom)
        }
        return classroomDTO
    }

    @Override
    @Throws(ResourceNotFoundException::class)
    fun deleteUser(login: String?): Map<String, Boolean> {
        val user: User = userRepository.findByLogin(login).orElseThrow(null)
        val response: Map<String, Boolean> = HashMap()
        if (user != null) {
            userRepository.delete(user)
            response.put("deleted", Boolean.TRUE)
            val pupil: Pupil = pupilRepository.findByUserId(user.getId())
            val teacher: Teacher = teacherRepository.findByUserId(user.getId())
            if (pupil != null) {
                pupil.setUserId(null)
                pupilRepository.save(pupil)
            } else if (teacher != null) {
                teacher.setUserId(null)
                teacherRepository.save(teacher)
            }
        } else {
            response.put("notDeleted", Boolean.FALSE)
        }
        return response
    }

    @Override
    @Throws(ResourceNotFoundException::class)
    fun blockUser(login: String?): Map<String, Boolean> {
        val user: User = userRepository.findByLogin(login).orElseThrow(null)
        val response: Map<String, Boolean> = HashMap()
        if (user != null) {
            user.setStatus("block")
            userRepository.save(user)
            response.put("blocked", Boolean.TRUE)
        } else {
            response.put("notBlocked", Boolean.FALSE)
        }
        return response
    }

    @Override
    fun unblockUser(login: String?): Map<String, Boolean> {
        val user: User = userRepository.findByLogin(login).orElseThrow(null)
        val response: Map<String, Boolean> = HashMap()
        if (user != null) {
            user.setStatus("unBlock")
            userRepository.save(user)
            response.put("unBlocked", Boolean.TRUE)
        } else {
            response.put("notUnBlocked", Boolean.FALSE)
        }
        return response
    }
}