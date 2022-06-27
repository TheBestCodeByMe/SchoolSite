package com.example.schoolsite;

import com.example.schoolsite.dto.UserDTO;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class SchoolSiteApplicationTests {

    @Autowired
    private UserService userService;

    private UserDTO expectedUser;

    @BeforeEach
    void init() {
        expectedUser = new UserDTO();
        expectedUser.setName("andreyLogin");
    }

    //@ParameterizedTest
    @DisplayName("Testing search user which exists in database")
    @Tag("USERS-TEST")
    //@ValueSource(ints = {4})
    @Test
    void testFindUserByIdWhichExists() throws ResourceNotFoundException {
        UserDTO actualUser = userService.getUserById(4L);
        //User actualUser = userService.findUserById(id);
        Assertions.assertEquals(expectedUser.getName(),actualUser.getName());
    }

    @Test
    @DisplayName("Testing search user which non exists in database")
    @Tag("USERS-TEST")
    void testFindUserByIdWhichNonExists() throws ResourceNotFoundException {
        UserDTO actualUser = userService.getUserById(2L);
        Assertions.assertNotEquals(expectedUser.getName(), actualUser.getName());
    }
}
