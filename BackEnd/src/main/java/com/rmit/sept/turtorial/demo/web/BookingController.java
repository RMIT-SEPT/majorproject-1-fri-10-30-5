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

    @PostMapping("/add")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }
        Booking booking1 = bookingService.addBooking(booking);
        return new ResponseEntity<Booking>(booking1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
        }
        Booking booking1 = bookingService.updateBooking(booking);
        return new ResponseEntity<Booking>(booking1, HttpStatus.CREATED);
    }

    @GetMapping("/{custID}/{bID}")
    public ResponseEntity<?> findBookingByCustIDAndBID(@Valid @PathVariable String custID, @PathVariable Long bID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Booking Object", HttpStatus.BAD_REQUEST);
//        }
        Booking booking1 = bookingService.findBookingByCustIDAndBID(custID, bID);
        return new ResponseEntity<Booking>(booking1, HttpStatus.CREATED);
    }

    @GetMapping("list/{custID}")
    public ResponseEntity<?> findAllBookingsByCustID(@Valid @PathVariable String custID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Booking> bookings = bookingService.findAllBookingsByCustID(custID);
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.CREATED);
    }
}
