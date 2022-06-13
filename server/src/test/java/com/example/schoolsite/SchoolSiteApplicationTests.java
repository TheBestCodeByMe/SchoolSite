package com.example.schoolsite;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class SchoolSiteApplicationTests {
    @BeforeEach
    void init() {
        userService = new UserService();
        expectedUser = new User();
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Thompson");
        expectedUser.setEmail("j@t.com");
        //expectedUser.setRole("ADMIN");
    }

    @ParameterizedTest
    @DisplayName("Testing search user which exists in database")
    @Tag("USERS-TEST")
    @ValueSource(ints = {1})
    void testFindUserByIdWhichExists(Integer id) {
        //User actualUser = userService.findUserById(id);
        //Assertions.assertEquals(expectedUser,actualUser);
    }

    @Test
    @DisplayName("Testing search user which non exists in database")
    @Tag("USERS-TEST")
    void testFindUserByIdWhichNonExists() {
        //User actualUser = userService.findUserById(2);
        //Assertions.assertNotEquals(expectedUser,actualUser);
    }

    @Test
    @DisplayName("Testing saving user success")
    void testSaveUserSuccess() {
        User actualUser = userService.saveUser(expectedUser);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Testing saving user failed")
    void testSaveUserFailed() {
        User actualUser = userService.saveUser(null);
        // Assertions.assertEquals(expectedUser, actualUser);
        // Что-то такое?
        // Только вместе IOException другое исключение?
        // Как-то я его не совсем правильно понимаю, наверное
        Assertions.assertThrows(actualUser, IOException);
    }

    @Test
    @DisplayName("Testing delete user which exists in database")
    @Tag("USERS-TEST")
    void testDeleteUserByIdWhichExists() {
        userService.delete(6);
        UserDTO actualUser = userService.findUserById(6);
        Assertions.assertNotEquals(null, actualUser);
    }

    @Test
    @DisplayName("Testing update user which exists in database")
    @Tag("USERS-TEST")
    void testUpdateUserWhichExists() {
        User updateUser = new User("6", "6", "1", LocalDate.of(2023,03,03));
        userService.update(5, updateUser);
        UserDTO actualUser = userService.findUserById(5);
        Assertions.assertNotEquals(updateUser, actualUser);
    }
}
