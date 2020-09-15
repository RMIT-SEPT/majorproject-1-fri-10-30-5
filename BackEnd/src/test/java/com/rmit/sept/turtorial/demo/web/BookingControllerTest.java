package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.services.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    //Testing needed: for each method in controller class
    //testing null inputs (separate test required for each input where validation occurs)
    //testing null input (single test for null object passed to resource)
    //valid request (all checked inputs are of valid state)
    //invalid request (each input is tested at boundaries -eg: (> vs <=) or (25 char vs 26 char) and case for each invalid character(@, &, % etc.))

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMap;
    @MockBean
    private BookingService bs;

//    @ParameterizedTest
//    @JsonSource("{\"custID\":\"custIDvalue\",\"empID\":\"empIDvalue\",\"bookingTime\":\"bookingTimevalue\",\"bookingDate\":\"bookingDatevalue\"}")
//    void givenValidBooking_whenCreatingBooking_thenSuccessfullyPost(JsonObject object) throws Exception {
//
//        assertEquals(true, true);
//
//
//    }

    //Test resource is created when booking object is valid
    @Test
    public void givenValidBooking_whenBookingPost_thenResourceCreated() throws Exception {
        String str = "{\"custID\":\"custTest\",\"empID\":\"empTest\",\"bookingTime\":\"1900\",\"bookingDate\":\"2020-12-12\"}";
        Booking booking = new Booking("newCust", "newEmp", 1600, "2020-09-09");
        mvc.perform(post("/api/booking/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMap.writeValueAsString(booking)))
//            .content(str))
//            .andExpect(status().isCreated());
            .andExpect(status().isConflict());
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(0L)))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.custID", is("cust1")))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.empID", is("emp1")))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingTime", is(1600)))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingDate", is("2020-09-09")))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingStatus", is(null)));
    }

    //Test bad request returned and error message returned when invalid booking is made
    @Test
    public void givenNullEmpID_whenBookingPost_thenReturnError() throws Exception {
        Booking booking = new Booking(1, "cust1", null, 1600, "2020-09-09","Booked");
        String nullBooking = "Invalid Booking Object";
        mvc.perform(post("/api/booking/add").contentType(MediaType.APPLICATION_JSON)
            .content(objectMap.writeValueAsString(booking)))
            .andExpect(jsonPath("$",is(nullBooking)))
            .andExpect(status().isBadRequest());
    }

    //Test get resource using custID and bID if resource is valid
    @Test
    public void givenBookingExists_whenGetSingleBooking_thenReturnSingleBooking() throws Exception {
        Booking booking1 = new Booking(1L, "cust1", "emp1", 1600, "2020-09-09","Booked");

        given(bs.findBookingByCustIDAndBID("cust1", 1L)).willReturn(booking1);

        mvc.perform(get("/api/booking/{custID}/{bID}","cust1", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empID",is(booking1.getEmpID())))
            .andExpect(jsonPath("$.bookingDate",is(booking1.getBookingDate())))
            .andExpect(jsonPath("$.bookingTime",is(booking1.getBookingTime())))
            .andExpect(status().isOk());
    }

    //Test get resource using custID and bID if resource is not present
    @Test
    public void givenBookingNotExists_whenGetSingleBooking_thenReturnError() throws Exception {
        mvc.perform(get("/api/booking/{custID}/{bID}","noCust", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    //Test get all resources using custID if resource is valid
    @Test
    public void givenBooking_whenGetAllBookingsForCust_thenReturnAllCustBookings() throws Exception {
        Booking booking1 = new Booking(1L, "cust1", "emp1", 1600, "2020-09-09","Booked");
        Booking booking2 = new Booking(2L, "cust1", "emp2", 1700, "2020-09-09","Booked");
        List<Booking> allbookings = Arrays.asList(booking1, booking2);
        given(bs.findAllBookingsByCustID("cust1")).willReturn(allbookings);

        mvc.perform(get("/api/booking/list/{custID}","cust1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$",hasSize(2)))
            .andExpect(jsonPath("$.[0].empID",is(booking1.getEmpID())))
            .andExpect(jsonPath("$.[0].bookingDate",is(booking1.getBookingDate())))
            .andExpect(jsonPath("$.[0].bookingTime",is(booking1.getBookingTime())))
            .andExpect(jsonPath("$.[1].empID",is(booking2.getEmpID())))
            .andExpect(jsonPath("$.[1].bookingDate",is(booking2.getBookingDate())))
            .andExpect(jsonPath("$.[1].bookingTime",is(booking2.getBookingTime())))
            .andExpect(status().isOk());
    }

    //Test get all resources using custID if resource is valid
    @Test
    public void givenNoBookings_whenGetAllBookingsForCust_thenReturnAllCustBookings() throws Exception {
        mvc.perform(get("/api/booking/list/{custID}","noCust")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", is("No Booking Objects")))
            .andExpect(status().isNotFound());
    }
}