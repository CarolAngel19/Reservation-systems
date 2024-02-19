package Bookingd.demo.services;

import Bookingd.demo.dto.BookingDto;
import Bookingd.demo.model.Booking;
import Bookingd.demo.repository.IBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private IBookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testGetAllBookings() {
        // Creación de objetos de reserva simulados para usar en las pruebas
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();

        // Configuración del comportamiento del mock del repositorio para
        // que devuelva una lista con los objetos simulados
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        // Llamar al método getAll para probarlo
        List<Booking> result = bookingService.getAllBooking();

        // Confirmar que el tamaño de la lista devuelta sea 2
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateBooking() {
        // Creación de un objeto de BookingDto simulado
        BookingDto bookingDto = new BookingDto();
        // Creación de un objeto de Booking esperado basado en el BookingDto simulado
        Booking expectedBooking = new Booking();

        // Configuración del comportamiento del mock del repositorio cuando se
        // llama a save
        when(bookingRepository.save(any())).thenReturn(expectedBooking);

        // Llamar al método createBooking para probarlo
        Booking result = bookingService.createBooking(bookingDto);

        // Confirmar que la reserva devuelta sea igual a la reserva esperada
        assertEquals(expectedBooking, result);
    }

    @Test
    public void testGetBookingById() {
        // Creación de un ID de prueba de reserva y un objeto de reserva esperado
        Long id = 1L;
        Booking expectedBooking = new Booking();

        // Configuración del comportamiento del mock del repositorio
        // para que devuelva el objeto de reserva esperado cuando se busque por ID
        when(bookingRepository.findById(id)).thenReturn(Optional.of(expectedBooking));

        // Llamar al método getBookingById para probarlo
        Optional<Booking> result = bookingService.getBookingById(id);

        // Confirmar que el Optional devuelto contenga el objeto de reserva esperado
        assertTrue(result.isPresent());
        assertEquals(expectedBooking, result.get());
    }

    @Test
    public void testGetBookingById_NotFound() {
        // Creación de un ID de prueba de reserva que no existe
        Long id = 1L;

        // Configuración del comportamiento del mock del repositorio para
        // que devuelva un Optional vacío cuando se busque por ID
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        // Llamar al método para probarlo
        Optional<Booking> result = bookingService.getBookingById(id);

        // Confirmar que el Optional devuelto esté vacío
        assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteBookingById() {
        // Creación de un ID de prueba de reserva y un objeto de reserva esperado
        Long id = 1L;
        Booking expectedBooking = new Booking();

        // Configuración del comportamiento del mock del repositorio
        // para que devuelva el objeto de reserva esperado cuando se busque por ID
        when(bookingRepository.findById(id)).thenReturn(Optional.of(expectedBooking));

        // Llamar al método para probarlo
        Booking result = bookingService.deleteBookingById(id);

        // Confirmar que el objeto de reserva devuelto sea el mismo que el esperado
        assertEquals(expectedBooking, result);

        // Confirmar que el método del repositorio se haya llamado una
        // vez con el ID simulado
        verify(bookingRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteBookingById_NotFound() {
        // Creación de un ID de prueba de reserva que no existe
        Long id = 1L;

        // Configuración del comportamiento del mock del repositorio para
        // que devuelva un Optional vacío cuando se busque por ID
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        // Llamar al método deleteBookingById para probarlo
        assertThrows(NoSuchElementException.class, () -> bookingService.deleteBookingById(id));
    }

    @Test
    public void testUpdateBooking() {
        // Creación de un ID de prueba de reserva, un objeto de BookingDto con datos
        // actualizados y un objeto de reserva existente
        Long id = 1L;
        BookingDto bookingDto = new BookingDto();
        Booking existingBooking = new Booking();

        // Creación de una reserva actualizada...
        Booking updatedBooking = new Booking();

        // Configuración del comportamiento del mock del repositorio para
        // que devuelva el objeto de reserva existente cuando se busque por ID
        when(bookingRepository.findById(id)).thenReturn(Optional.of(existingBooking));
        // Configuración del comportamiento del mock del repositorio para que
        // devuelva el objeto de reserva actualizado cuando se guarde
        when(bookingRepository.save(any())).thenReturn(updatedBooking);

        // Llamar al método para probarlo
        Booking result = bookingService.updateBooking(id, bookingDto);

        // Confirmar que la reserva devuelta sea igual a la reserva actualizada
        assertEquals(updatedBooking, result);
    }
}