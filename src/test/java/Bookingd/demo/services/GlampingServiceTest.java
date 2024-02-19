package Bookingd.demo.services;

import Bookingd.demo.dto.GlampingDto;
import Bookingd.demo.model.Glamping;
import Bookingd.demo.repository.IGlampingRepository;
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
public class GlampingServiceTest {

    @Mock
    private IGlampingRepository glampingRepository;

    @InjectMocks
    private GlampingService glampingService;

    @Test
    public void testGetAllGlamping() {
        // Creacion objetos de Glamping simulados para usar en la pruebas
        Glamping glamping1 = new Glamping();
        Glamping glamping2 = new Glamping();

        // Configuracion del comportamiento del mock del repositorio para
        // que devuelva una lista con los objetos simulados
        when(glampingRepository.findAll()).thenReturn(Arrays.asList(glamping1, glamping2));

        // Llamar el método getAllGlamping para probarlo
        List<Glamping> result = glampingService.getAllGlamping();

        // Confirmar que el tamaño de la lista devuelta sea 2
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateGlamping() {
        // Creacion de un objeto de GlampingDto simulado
        GlampingDto glampingDto = new GlampingDto();
        // Creacion de un objeto de Glamping esperado basado en el GlampingDto simulado
        Glamping expectedGlamping = new Glamping();

        // Configuracion del comportamiento del mock del repositorio cuando se
        // llama a save
        when(glampingRepository.save(any())).thenReturn(expectedGlamping);

        // Llamar al método createGlamping para probarlo
        Glamping result = glampingService.createGlamping(glampingDto);

        // Confirmar que el Glamping devuelto sea igual al Glamping esperado
        assertEquals(expectedGlamping, result);
    }

    @Test
    public void testGetGlampingById() {
        // Creacion un ID de prueba de Glamping y un objeto Glamping esperado
        Long id = 1L;
        Glamping expectedGlamping = new Glamping();

        // Configuracion del comportamiento del mock del repositorio
        // para que devuelva el objeto Glamping esperado cuando se busque por ID
        when(glampingRepository.findById(id)).thenReturn(Optional.of(expectedGlamping));

        // LLamar el método getGlampingById para probarlo
        Optional<Glamping> result = glampingService.getGlampingById(id);

        // Confirmar que el Optional devuelto contenga el objeto Glamping esperado
        assertTrue(result.isPresent());
        assertEquals(expectedGlamping, result.get());
    }

    @Test
    public void testGetGlampingById_NotFound() {
        // Creacion de ID de prueba de Glamping que no existe
        Long id = 1L;

        // Configuracion del comportamiento del mock del repositorio para
        // que devuelva un Optional vacío cuando se busque por ID
        when(glampingRepository.findById(id)).thenReturn(Optional.empty());

        // Llamar el método para probarlo
        Optional<Glamping> result = glampingService.getGlampingById(id);

        // Confirmar que el Optional devuelto esté vacío
        assertFalse(result.isPresent());
    }


    @Test
    public void testDeleteGlampingById() {
        // Creacion de ID de prueba de Glamping y un objeto Glamping esperado
        Long id = 1L;
        Glamping expectedGlamping = new Glamping();

        // Configuracion del comportamiento del mock del repositorio
        // para que devuelva el objeto Glamping esperado cuando se busque por ID
        when(glampingRepository.findById(id)).thenReturn(Optional.of(expectedGlamping));

        // Llamer el método para probarlo
        Glamping result = glampingService.deleteGlampingById(id);

        // Confirmar que el objeto Glamping devuelto sea el mismo que el esperado
        assertEquals(expectedGlamping, result);

        // Confirmar que el método del repositorio se haya llamado una
        // vez con el ID simulado
        verify(glampingRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteGlampingById_NotFound() {
        // Creacion de ID de prueba de Glamping que no existe
        Long id = 1L;

        // Configuracion del comportamiento del mock del repositorio para
        // que devuelva un Optional vacío cuando se busque por ID
        when(glampingRepository.findById(id)).thenReturn(Optional.empty());

        // Invocamos el método deleteGlampingById probarlo
        assertThrows(NoSuchElementException.class, () -> glampingService.deleteGlampingById(id));
    }


    @Test
    public void testUpdateGlamping() {
        // Creacion de ID de prueba de Glamping, un objeto GlampingDto con datos
        // actualizados y un objeto Glamping existente
        Long id = 1L;
        GlampingDto glampingDto = new GlampingDto();
        Glamping existingGlamping = new Glamping();

        // Creacion de Glamping actualizado...
        Glamping updatedGlamping = new Glamping();

        // Configuracion del comportamiento del mock del repositorio para
        // que devuelva el objeto Glamping existente cuando se busque por ID
        when(glampingRepository.findById(id)).thenReturn(Optional.of(existingGlamping));
        // Configuracion del comportamiento del mock del repositorio para que
        // devuelva el objeto Glamping actualizado cuando se guarde
        when(glampingRepository.save(any())).thenReturn(updatedGlamping);

        // Llamar el método para probarlo
        Glamping result = glampingService.updateGlamping(id, glampingDto);

        // Confimar que el Glamping devuelto sea igual al Glamping actualizado
        assertEquals(updatedGlamping, result);

    }

}


