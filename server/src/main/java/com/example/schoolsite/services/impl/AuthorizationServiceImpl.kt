package com.example.schoolsite.services.impl

import com.example.schoolsite.entity.Pupil

@Service
@RequiredArgsConstructor
class AuthorizationServiceImpl : AuthorizationService {
    private val authenticationManager: AuthenticationManager? = null
    private val userRepository: UserRepository? = null
    private val pupilRepository: PupilRepository? = null
    private val teacherRepository: TeacherRepository? = null
    private val roleRepository: RoleRepository? = null
    private val passwordEncoder: PasswordEncoder? = null
    private val jwtUtils: JwtUtils? = null
    @Override
    fun authUser(loginRequest: LoginRequest): JwtResponse? {
        val authentication: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()))

        // формируем jwt токен
        SecurityContextHolder.getContext().setAuthentication(authentication)
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailsImpl = authentication.getPrincipal()
        val roles: List<String> = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
        return if (userDetails.getId() != null) {
            JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles)
        } else {
            null
        }
    }

    @Override
    fun registerUser(signupRequest: SignUpRequest): String {
        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return "Error: Username is exist"
        }
        val pupil: Pupil = pupilRepository.findByNameAndLastnameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic())
        val teacher: Teacher = teacherRepository.findByNameAndLastNameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic())
        if (signupRequest.getRole().equals("pupil")) {
            if (pupil == null) {
                return "Error: Pupil is not exist"
            }
        } else if (signupRequest.getRole().equals("teacher") || signupRequest.getRole().equals("Director")) {
            if (teacher == null) {
                return "Error: Teacher is not exist"
            }
        }
        val user = User(signupRequest.getLogin(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getStatus())
        val reqRoles: Set<String> = signupRequest.getRole()
        val roles: Set<Role> = HashSet()
        if (reqRoles == null) {
            return "Error: Your role is null"
            /*            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
              roles.add(userRole);*/
        } else {
            reqRoles.forEach { r ->
                when (r) {
                    "pupil" -> {
                        val adminRole: Role = roleRepository
                                .findByName(ERole.ROLE_PUPIL)
                                .orElseThrow { RuntimeException("Error, Role Pupil is not found") }
                        roles.add(adminRole)
                    }

                    "teacher" -> {
                        val modRole: Role = roleRepository
                                .findByName(ERole.ROLE_TEACHER)
                                .orElseThrow { RuntimeException("Error, Role Teacher is not found") }
                        roles.add(modRole)
                    }
                }
            }
        }
        user.setRoles(roles)
        userRepository.save(user)
        val userForId: User = userRepository.findByLogin(user.getLogin()).orElse(null)
        val role: Set<Role> = HashSet()
        role.add(Role(ERole.ROLE_PUPIL))
        if (userForId.getRoles().equals(role)) {
            pupil.setUserId(userRepository.getById(userForId.getId()))
            pupil.setEmail(signupRequest.getEmail())
            pupilRepository.save(pupil)
        } else {
            teacher.setUserId(userRepository.getById(userForId.getId()))
            teacher.setEmail(signupRequest.getEmail())
            teacherRepository.save(teacher)
        }
        return "User CREATED"
    }
}