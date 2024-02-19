package Bookingd.demo.controller;

import Bookingd.demo.dto.BookingDto;
import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.Booking;
import Bookingd.demo.model.User;
import Bookingd.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("all")
    public ResponseEntity<?> getAllBooking(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBooking());
        }catch (Exception e){
            System.out.println("Error get all booking");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDto bookingDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.createBooking(bookingDto));
        }catch (Exception e){
            System.out.println("Error create booking");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllBookingById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(id));
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.deleteBookingById(id));
        }catch (Exception e){
            System.out.println("Error create users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto){
        try {
            Booking updatedBooking = bookingService.updateBooking(id, bookingDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
        } catch (Exception e){
            System.out.println("Error updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
