package Bookingd.demo.controller;

import Bookingd.demo.dto.BookingDto;
import Bookingd.demo.model.Booking;
import Bookingd.demo.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBooking() {
        // Simular el comportamiento del servicio
        when(bookingService.getAllBooking()).thenReturn(Arrays.asList(new Booking(), new Booking()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = bookingController.getAllBooking();

        // Verificar el código de estado y el tamaño de la lista devuelta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Booking> bookings = (List<Booking>) responseEntity.getBody();
        assertEquals(2, bookings.size());
    }

    @Test
    void testCreateBooking() {
        // Crear un objeto de BookingDto simulado
        BookingDto bookingDto = new BookingDto();

        // Simular el comportamiento del servicio
        when(bookingService.createBooking(any(BookingDto.class))).thenReturn(new Booking());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = bookingController.createBooking(bookingDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetBookingById() {
        // Simular el comportamiento del servicio
        when(bookingService.getBookingById(anyLong())).thenReturn(Optional.of(new Booking()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = bookingController.getAllBookingById(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteBooking() {
        // Simular el comportamiento del servicio
        when(bookingService.deleteBookingById(anyLong())).thenReturn(new Booking());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = bookingController.deleteBooking(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateBooking() {
        // Crear un objeto de BookingDto simulado
        BookingDto bookingDto = new BookingDto();

        // Simular el comportamiento del servicio
        when(bookingService.updateBooking(anyLong(), any(BookingDto.class))).thenReturn(new Booking());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = bookingController.updateBooking(1L, bookingDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}