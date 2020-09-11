package com.rmit.sept.turtorial.demo.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

//needed for springboot features in junit
@RunWith(SpringRunner.class)
//auto configures mockmvc
@WebMvcTest(WorkingHoursController.class)
public class WorkingHoursControllerTest {

    //sends post/gets to url etc.
    @Autowired
    private MockMvc mvc;

    //convert object to json string
    @Autowired
    private ObjectMapper objectMap;

    //creating mock of workinghour service class
    @MockBean
    private  WorkingHoursService whs;


    @Test
    public void givenWorkingHours_whenPostWorkingHours_thenSuccessfullyPost() throws Exception {
       //mocks the data from user
        WorkingHours workHour = new WorkingHours(0L, "1",1200,1600,"2020-12-12","Hair");
        //mock https request
        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(status().isCreated());
       //         .andExpect(MockMvcResultMatchers.jsonPath("$.shiftNo").exists());
    }

    @Test
    public void givenNullWorkingHours_whenPostWorkingHours_thenReturnError() throws Exception {
        WorkingHours workHour = new WorkingHours(null, null,1200,1600,"2020-12-12","Hair");
        String nullWork = "Invalid Working_Hours Object";
        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(nullWork)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenWorkingHours_whenGetEmployees_thenReturnArray() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,1600,"2020-12-12","Hair");
        List<WorkingHours> allWorkingHours = Arrays.asList(workHour);

        given(whs.findAllByEmpIDEquals("1")).willReturn(allWorkingHours);

        mvc.perform(get("/api/workinghours/list/{empID}","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].empID",is(workHour.getEmpID())))
                .andExpect(jsonPath("$[0].startTime",is(workHour.getStartTime())))
                .andExpect(jsonPath("$[0].workDate",is(workHour.getWorkDate())))
                .andExpect(status().isCreated());
    }


//    @Test
//    public void givenNullWorkingHours_whenAddEmployeeID_thenReturnNullArray() throws Exception {
//
//        given(whs.findAllByEmpIDEquals("")).willReturn(null);
//
//        mvc.perform(get("/api/workinghours/list/{empID}","").contentType(MediaType.APPLICATION_JSON))
//           //      .andExpect(status().isOk())
//                .andExpect(jsonPath("$",is("")));
//    }







    //    WorkingHoursControllerTest whc;
//
//    @BeforeAll
//    static void initAll() {
//        //code before anu tests are run
//    }
//
//    @BeforeEach
//    void init() {
//       WorkingHoursService whs;
//        whc = new WorkingHoursControllerTest();
//    }
//
//    @DisplayName("Custom name for test \uD83D\uDE31")
//    @Test
//    void addNewWH() {
//        WorkingHours workingHours = new WorkingHours();
//
//        boolean wh = false;
//        if(whc.getClass() == whc.getClass()){
//            wh = true;
//        }
//        assertEquals(true, wh);
//    }
//
//    @Test
//    void succeedingTest() {
//    }
//
//    @Test
//    void failingTest() {
//        //fail("a failing test");
//        assertEquals(true, false);
//    }
//
//    @Test
//    @Disabled("for demonstration purposes")
//    void skippedTest() {
//        // not executed
//    }
//
////    @Test
////    void abortedTest() {
////        assumeTrue(whc.addNewWH());
////        fail("test should have been aborted");
////    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @AfterAll
//    static void tearDownAll() {
//        //code to clean up after all tests are run
//    }
}
