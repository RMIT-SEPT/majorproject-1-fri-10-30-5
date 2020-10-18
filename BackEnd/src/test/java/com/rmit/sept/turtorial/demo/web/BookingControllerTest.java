package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.services.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//This class contains all tests for the Booking Controller class.
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest
{
    //Instance of the MockMvc Object
    @Autowired
    private MockMvc mvc;

    //Instance of ObjectMapper Object
    @Autowired
    private ObjectMapper objectMap;

    //Instance of Booking Service Object
    @MockBean
    private BookingService bs;

    //Error message for No Booking Objects
    private final String noObjs = "No Booking Objects";

    //Before each method to reset each object after each test
    @BeforeEach
    void init()
    {
        reset(mvc);
        reset(objectMap);
        reset(bs);
    }

    //Tests that booking is created when booking object is valid
    @Test
    public void givenValidBooking_whenBookingPost_thenCreated() throws Exception
    {
        Booking booking = new Booking(1L, "newCust", "newEmp",
                1600, "2020-09-22", "booked");
        Booking booking1 = new Booking("newCust", "newEmp",
                1600, "2020-09-22");
        when(bs.addBooking(any(Booking.class))).thenReturn(booking);

        mvc.perform(post("/api/booking/add")
            .content(objectMap.writeValueAsString(booking1))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.custID", is("newCust")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.empID", is("newEmp")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingTime", is(1600)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingDate", is("2020-09-22")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.bookingStatus", is("booked")));
    }

    //Tests that when invalid booking is made, a bad request and error message are returned
    @Test
    public void givenNullEmpID_whenBookingPost_thenBadRequest() throws Exception
    {
        Booking booking = new Booking(1, "cust1", null, 1600,
                "2020-09-09","Booked");
        String nullBooking = "Invalid Booking Object";
        mvc.perform(post("/api/booking/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMap.writeValueAsString(booking)))
            .andExpect(jsonPath("$",is(nullBooking)))
            .andExpect(status().isBadRequest());
    }

    //Tests that get booking using a custID and bID works when a valid object exists
    @Test
    public void givenBookingExists_whenGetSingleBooking_thenOk() throws Exception
    {
        Booking booking1 = new Booking(1L, "cust1", "emp1", 1600,
                "2020-09-09","Booked");

        given(bs.findBookingByCustIDAndBID("cust1", 1L)).willReturn(booking1);

        mvc.perform(get("/api/booking/{custID}/{bID}","cust1", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.empID",is(booking1.getEmpID())))
            .andExpect(jsonPath("$.bookingDate",is(booking1.getBookingDate())))
            .andExpect(jsonPath("$.bookingTime",is(booking1.getBookingTime())))
            .andExpect(status().isOk());
    }

    /*
        Tests that get booking returns not found request
        and error message when the object doesn't exist
     */
    @Test
    public void givenBookingNotExists_whenGetSingleBooking_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/booking/{custID}/{bID}","noCust", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", is("No Booking Object")))
            .andExpect(status().isNotFound());
    }

    //Tests that all bookings are returned if resource is valid
    @Test
    public void givenTwoBookings_whenGetAllBookingsForCust_thenOk() throws Exception
    {
        Booking booking1 = new Booking(1L, "cust1", "emp1",
                1600, "2020-09-09","Booked");
        Booking booking2 = new Booking(2L, "cust1", "emp2",
                1700, "2020-09-09","Booked");

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

    /*
        Tests that get all bookings for a customer returns a not found
        request and an error message if resource doesn't exist
     */
    @Test
    public void givenNoBookings_whenGetAllBookingsForCust_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/booking/list/{custID}","noCust")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", is(noObjs)))
            .andExpect(status().isNotFound());
    }

    //Tests that get all past booking resources works if resource is valid
    @Test
    public void givenTwoPastBookings_whenGetAllPastBookingsForCust_thenOk() throws Exception
    {
        Booking booking1 = new Booking(1L, "cust1", "emp1", 1600,
                "2010-09-09","complete");
        Booking booking2 = new Booking(2L, "cust1", "emp2", 1700,
                "2019-09-09","complete");

        List<Booking> pastBookings = Arrays.asList(booking1, booking2);
        given(bs.findAllPastOrUpcomingBookingsByCustID("cust1", true)).willReturn(pastBookings);

        mvc.perform(get("/api/booking/pastBookings/list/{custID}","cust1")
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

    /*
        Tests that when requesting all past booking resources and none
        exist, an error message and a not found request is returned
     */
    @Test
    public void givenNoPastBookings_whenGetAllPastBookingsForCust_thenNootFound() throws Exception
    {
        mvc.perform(get("/api/booking/pastBookings/list/{custID}","cust1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(noObjs)))
                .andExpect(status().isNotFound());
    }

    //Test get all upcoming booking resources using custID if resource is valid
    @Test
    public void givenTwoFutureBookings_whenGetAllFutureBookingsForCust_thenOk() throws Exception
    {
        Booking booking1 = new Booking(1L, "cust1", "emp1", 1600,
                "2029-09-09","Booked");
        Booking booking2 = new Booking(2L, "cust1", "emp2", 1700,
                "2025-09-09","Booked");

        List<Booking> upcomingBookings = Arrays.asList(booking1, booking2);
        given(bs.findAllPastOrUpcomingBookingsByCustID("cust1", false))
                .willReturn(upcomingBookings);

        mvc.perform(get("/api/booking/upcomingBookings/list/{custID}","cust1")
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

    /*
        Tests that if no upcoming booking resources exist
        a not found request and an error message is returned
     */
    @Test
    public void givenNoFutureBookings_whenGetAllFutureBookingsForCust_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/booking/upcomingBookings/list/{custID}","cust1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(noObjs)))
                .andExpect(status().isNotFound());
    }
}