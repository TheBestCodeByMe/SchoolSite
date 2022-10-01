package com.example.schoolsite.services.impl

import com.example.schoolsite.dto.UserDTO

@Service
@RequiredArgsConstructor
class UserServiceImpl : UserService {
    private val userRepository: UserRepository? = null
    private val pupilRepository: PupilRepository? = null
    private val teacherRepository: TeacherRepository? = null

    @get:Override
    val allUsers: List<Any>
        get() = userRepository.findAll()

    @Override
    @Throws(ResourceNotFoundException::class)
    fun getUserById(userId: Long): UserDTO {
        val user: Optional<User> = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow { ResourceNotFoundException("User not found for this id :: $userId") })
        val pupil: Pupil = pupilRepository.findByUserId(userId)
        val teacher: Teacher = teacherRepository.findByUserId(userId)
        val userDTO: UserDTO
        userDTO = if (pupil != null) {
            Mapper.mapUserToUserDTO(user.get(), pupil)
        } else {
            Mapper.mapUserTeacherToUserDTO(user.get(), teacher)
        }
        return userDTO
    }

    @Override
    @Throws(ResourceNotFoundException::class)
    fun updateUser(userId: Long,
                   userDetails: UserDTO): UserDTO {
        val user: User = userRepository.findById(userId)
                .orElseThrow { ResourceNotFoundException("User not found for this id :: $userId") }
        val pupil: Pupil = pupilRepository.findByUserId(userId)
        val teacher: Teacher = teacherRepository.findByUserId(userId)
        if (pupil != null) {
            pupil.setEmail(userDetails.getEmail())
            pupil.setName(userDetails.getName())
            pupil.setLastname(userDetails.getLastname())
            pupil.setPatronymic(userDetails.getPatronymic())
            pupilRepository.save(pupil)
        } else {
            teacher.setEmail(userDetails.getEmail())
            teacher.setName(userDetails.getName())
            teacher.setLastName(userDetails.getLastname())
            teacher.setPatronymic(userDetails.getPatronymic())
            teacherRepository.save(teacher)
        }
        user.setLogin(userDetails.getLogin())
        userRepository.save(user)
        return userDetails
    }
}