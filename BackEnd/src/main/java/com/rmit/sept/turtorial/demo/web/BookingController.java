package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

//This class allows for operations to be performed on Booking objects.
@CrossOrigin
@RestController
@RequestMapping("/api/booking")
public class BookingController
{
    //Instance of Booking Service
    @Autowired
    private BookingService bookingService;

    //Adds a booking object using a POST request
    @PostMapping("/add")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result) throws ParseException {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }
        booking.setBookingStatus("booked");
        Booking booking1 = bookingService.addBooking(booking);

        if (booking1 != null)
        {
            return new ResponseEntity<>(booking1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Booking Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    //Updates the booking status using a PUT request
    @PutMapping("/update")
    public ResponseEntity<?> updateBooking(@Valid @RequestBody Booking booking, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }
        Booking booking1 =  bookingService.updateBooking(booking);
        if (booking1 != null){
            return new ResponseEntity<>(booking1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Booking Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    //Returns a booking for a customer using a GET request
    @GetMapping("/{custID}/{bID}")
    public ResponseEntity<?> findBookingByCustIDAndBID(@Valid @PathVariable String custID, @PathVariable Long bID)
    {
        Booking booking1 = bookingService.findBookingByCustIDAndBID(custID, bID);
        if (booking1 != null)
        {
            return new ResponseEntity<>(booking1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Object", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of bookings for a customer using a GET request
    @GetMapping("/list/{custID}")
    public ResponseEntity<?> findAllBookingsByCustID(@Valid @PathVariable String custID)
    {
        List<Booking> bookings = bookingService.findAllBookingsByCustID(custID);
        if (bookings.size() != 0)
        {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of all past bookings for a customer using a GET request
    @GetMapping("/pastBookings/list/{custID}")
    public ResponseEntity<?> findAllPastBookingsByCustID(@Valid @PathVariable String custID)
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookingsByCustID(custID, true);
        if (bookings.size() != 0)
        {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of all upcoming bookings for a customer using a GET request
    @GetMapping("/upcomingBookings/list/{custID}")
    public ResponseEntity<?> findAllUpcomingBookingsByCustID(@Valid @PathVariable String custID)
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookingsByCustID(custID, false);
        if (bookings.size() != 0)
        {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of all past bookings using a GET request
    @GetMapping("admin/past-Bookings")
    public ResponseEntity<?> getPastBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(true);
        if (bookings.size() != 0)
        {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of all upcoming bookings using a GET request
    @GetMapping("admin/upcoming-Bookings")
    public ResponseEntity<?> getUpcomingBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(false);
        if (bookings.size() != 0)
        {
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }
}