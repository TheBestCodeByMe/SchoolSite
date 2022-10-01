package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.PupilDTO

@Service
@RequiredArgsConstructor
class PupilServiceImpl : PupilService {
    private val pupilRepository: PupilRepository? = null
    private val parentsRepository: ParentsRepository? = null
    private val classroomRepository: ClassroomRepository? = null
    @Override
    fun getPupilByFIO(userId: String?): PupilDTO {
        val pupil: Pupil = pupilRepository.findByUserId(Long.parseLong(userId))
        val parents: Parents = parentsRepository.getById(pupil.getParentsId().getId())
        val classroom: Classroom = classroomRepository.getById(pupil.getClassroomId().getId())
        return Mapper.mapToPupilDTO(pupil, parents, classroom)
    }
}