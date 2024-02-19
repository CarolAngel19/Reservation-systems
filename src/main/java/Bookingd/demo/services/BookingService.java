package Bookingd.demo.services;

import Bookingd.demo.dto.BookingDto;
import Bookingd.demo.model.Booking;
import Bookingd.demo.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    IBookingRepository iBookingRepository;

    public List<Booking> getAllBooking(){
        List<Booking> bookings =  iBookingRepository.findAll();
        System.out.println("Booking: " + bookings);
        return bookings;
    }

    public Booking createBooking(BookingDto bookingDto){

        Booking newBooking = new Booking();
        newBooking.setUser_id(bookingDto.getUser_id());
        newBooking.setId_glamping(bookingDto.getId_glamping());
        newBooking.setNumber_of_adults(bookingDto.getNumber_of_adults());
        newBooking.setNumber_of_children(bookingDto.getNumber_of_children());
        newBooking.setDate_entry(bookingDto.getDate_entry());
        newBooking.setDate_exit(bookingDto.getDate_exit());
        newBooking.setHour_entry(bookingDto.getHour_entry());
        newBooking.setHour_exit(bookingDto.getHour_exit());

        return iBookingRepository.save(newBooking);
    }

    public Optional<Booking> getBookingById(Long id){
        return iBookingRepository.findById(id);
    }

    public Booking deleteBookingById(Long id) {
        Booking booking = getBookingById(id).get();
        iBookingRepository.deleteById(id);
        return booking;
    }

    public Booking updateBooking(Long id, BookingDto bookingDto){
        Booking existingBooking = iBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));


        existingBooking.setUser_id(bookingDto.getUser_id());
        existingBooking.setId_glamping(bookingDto.getId_glamping());
        existingBooking.setNumber_of_adults(bookingDto.getNumber_of_adults());
        existingBooking.setNumber_of_children(bookingDto.getNumber_of_children());
        existingBooking.setDate_entry(bookingDto.getDate_entry());
        existingBooking.setDate_exit(bookingDto.getDate_exit());
        existingBooking.setHour_entry(bookingDto.getHour_entry());
        existingBooking.setHour_exit(bookingDto.getHour_exit());

        return iBookingRepository.save(existingBooking);
    }
}
