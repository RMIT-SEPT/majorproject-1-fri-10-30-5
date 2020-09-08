package com.rmit.sept.turtorial.demo.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class WorkingHoursControllerTest {
    WorkingHoursControllerTest whc;

    @BeforeAll
    static void initAll() {
        //code before anu tests are run
    }

    @BeforeEach
    void init() {
        //code to get ready before each test
        whc = new WorkingHoursControllerTest();
    }

    @DisplayName("Custom name for test \uD83D\uDE31")
    @Test
    void addNewWH() {
        boolean wh = false;
        if(whc.getClass() == whc.getClass()){
            wh = true;
        }
        assertEquals(true, wh);
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        //fail("a failing test");
        assertEquals(true, false);
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

//    @Test
//    void abortedTest() {
//        assumeTrue(whc.addNewWH());
//        fail("test should have been aborted");
//    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
        //code to clean up after all tests are run
    }
}
