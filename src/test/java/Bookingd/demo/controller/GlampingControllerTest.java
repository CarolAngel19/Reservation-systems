package Bookingd.demo.controller;

import Bookingd.demo.dto.GlampingDto;
import Bookingd.demo.model.Glamping;
import Bookingd.demo.services.GlampingService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class GlampingControllerTest {

    @Mock
    private GlampingService glampingService;

    @InjectMocks
    private GlampingController glampingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGlamping() {
        // Simular el comportamiento del servicio
        when(glampingService.getAllGlamping()).thenReturn(Arrays.asList(new Glamping(), new Glamping()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = glampingController.getAllGlamping();

        // Verificar el código de estado y el tamaño de la lista devuelta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Glamping> glampings = (List<Glamping>) responseEntity.getBody();
        assertEquals(2, glampings.size());
    }

    @Test
    void testCreateGlamping() {
        // Crear un objeto de GlampingDto simulado
        GlampingDto glampingDto = new GlampingDto();

        // Simular el comportamiento del servicio
        when(glampingService.createGlamping(any(GlampingDto.class))).thenReturn(new Glamping());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = glampingController.createGlamping(glampingDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetGlampingById() {
        // Simular el comportamiento del servicio
        when(glampingService.getGlampingById(anyLong())).thenReturn(Optional.of(new Glamping()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = glampingController.getAllGlampingById(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteGlamping() {
        // Simular el comportamiento del servicio
        when(glampingService.deleteGlampingById(anyLong())).thenReturn(new Glamping());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = glampingController.deleteGlamping(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateGlamping() {
        // Crear un objeto de GlampingDto simulado
        GlampingDto glampingDto = new GlampingDto();

        // Simular el comportamiento del servicio
        when(glampingService.updateGlamping(anyLong(), any(GlampingDto.class))).thenReturn(new Glamping());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = glampingController.updateGlamping(1L, glampingDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}