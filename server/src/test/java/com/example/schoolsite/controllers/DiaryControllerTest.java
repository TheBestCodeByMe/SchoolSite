package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.DiaryDTO;
import com.example.schoolsite.entity.Role;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.enumiration.ERole;
import com.example.schoolsite.pojo.JwtResponse;
import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.SignUpRequest;
import com.example.schoolsite.services.DiaryServiceImpl;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@RunWith(Spring.runner)
class DiaryControllerTest {

    /*class SQLUsersTest {
private final UsersRepository userRepository;
private static Users user;

@BeforeEach void setUp() {
user = new User ("1", "1");
}

@Test
void testFindUsersByLoginAndPassword() {
 Assertions.assertEquals(user, userRepository.findByLoginAndPassword(user)); } }
*/

    /*
class UserServiceTest {

    User expectedUser;
    static UserService userService = new UserService(); //лучше назвать sut (system under test)

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
    * */
/*
    DiaryController diaryController;
    List<DiaryDTO> dtoList;

    @BeforeEach
    void init() {
        diaryController = new DiaryController();
        dtoList = diaryController.getDiaryDTOByUser(5L);
    }

    @Test
    void addAttendanceAndAcademicPerfomance() {
    }

    @Test
    void getDiaryByUserWhichExist() {
        //User actualUser = userService.findUserById(2);
        Long id = 5L;
        Assertions.assertEquals(dtoList, diaryController.getDiaryDTOByUser(id));
    }

    @Test
    void getDiaryByUserWhichNotExist() {
        //User actualUser = userService.findUserById(2);
        Long id = 4L;
        Assertions.assertNotEquals(dtoList, diaryController.getDiaryDTOByUser(id));
    }

    @Test
    void getNumberAttendance() {
        Assertions.assertEquals(1, diaryController.getNumbAttendance(5L));
    }

    @Test
    void getNumberAttendanceNull() {
        Assertions.assertEquals("", diaryController.getNumbAttendance(24L));
    }

    @Test
    void getAvrgGrade() {
        Assertions.assertEquals("", diaryController.getNumbAttendance(24L));
    }

    @Test
    void getAvrgGradeNull() {
        Assertions.assertEquals("У ученика ещё нет оценок", diaryController.getNumbAttendance(24L));
    }

    @Test
    void getUserById() {
    }

    @Test
    void getSaveDiary() {
    }*/
}