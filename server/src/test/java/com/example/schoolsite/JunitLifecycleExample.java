package com.example.schoolsite;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JunitLifecycleExample {
/*
    Long counter;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll annotated method");
        Long userCounter = 12L;
    }

    @BeforeEach
    void setupBeforeEach(TestInfo info) {
        System.out.println("@BeforeEach annotated method");
        System.out.println(info.getDisplayName());
        counter = 155L;
    }

    @ParameterizedTest
    @CsvSource({"First_Name, Last_Name"})
    @DisplayName("Simple test")
    void testEqualsName(String userName, String lastName, TestInfo info) {
        String expectedFirstName = "First_Name";
        String expectedLastName = "Last_Name";
        System.out.println(info.getDisplayName());
        assertEquals(expectedFirstName, userName);
        assertEquals(expectedLastName, lastName);
    }

    @AfterEach
    void cleanUpEachTest() {
        System.out.println("@AfterEach annotated method");
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("@AfterAll annotated method");
    }
*/
    //@Nested
    //class Calculator {
    //    @RepeatedTest(5)
    //    void calculateTestOne() {
    //        System.out.println("@Test annotated method");
    //        Long expected = 156L;
    //        Long actual = counter + 1;
    //        assertEquals(expected, actual);
    //    }
    //}

}