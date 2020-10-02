package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //Check if start time has already been added
    @PostMapping("/add")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }

        booking.setBookingStatus("booked");

        Booking booking1 = bookingService.addBooking(booking);
        if (booking1 != null){
            return new ResponseEntity<Booking>(booking1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Booking Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }
        Booking booking1 = bookingService.updateBooking(booking);
        if (booking1 != null){
            return new ResponseEntity<Booking>(booking1, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Booking Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{custID}/{bID}")
    public ResponseEntity<?> findBookingByCustIDAndBID(@Valid @PathVariable String custID, @PathVariable Long bID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
//        }
        Booking booking1 = bookingService.findBookingByCustIDAndBID(custID, bID);
        if (booking1 != null){
            return new ResponseEntity<Booking>(booking1, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Object", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/list/{custID}")
    public ResponseEntity<?> findAllBookingsByCustID(@Valid @PathVariable String custID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Booking> bookings = bookingService.findAllBookingsByCustID(custID);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pastBookings/list/{custID}")
    public ResponseEntity<?> findAllPastBookingsByCustID(@Valid @PathVariable String custID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookingsByCustID(custID, true);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/upcomingBookings/list/{custID}")
    public ResponseEntity<?> findAllUpcomingBookingsByCustID(@Valid @PathVariable String custID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookingsByCustID(custID, false);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/past-Bookings")
    public ResponseEntity<?> getPastBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(true);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/upcoming-Bookings")
    public ResponseEntity<?> getUpcomingBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(false);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }
}


