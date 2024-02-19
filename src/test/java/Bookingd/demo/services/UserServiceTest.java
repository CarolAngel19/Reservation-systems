package Bookingd.demo.services;

import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.User;
import Bookingd.demo.repository.IUserRepository;
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
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        // Crear usuarios simulados
        User user1 = new User();
        User user2 = new User();

        // Configurar el comportamiento del mock del repositorio para devolver una lista con los usuarios simulados
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Llamar al método getAllUsers para probarlo
        List<User> result = userService.getAllUsers();

        // Confirmar que el tamaño de la lista devuelta sea 2
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateUser() {
        // Crear un objeto UserDto simulado
        UserDto userDto = new UserDto();
        // Crear un objeto User esperado basado en el UserDto simulado
        User expectedUser = new User();

        // Configurar el comportamiento del mock del repositorio cuando se llama a save
        when(userRepository.save(any())).thenReturn(expectedUser);

        // Llamar al método createUser para probarlo
        User result = userService.createUser(userDto);

        // Confirmar que el User devuelto sea igual al User esperado
        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUserById() {
        // Crear un ID de prueba de usuario y un objeto User esperado
        Long id = 1L;
        User expectedUser = new User();

        // Configurar el comportamiento del mock del repositorio para que devuelva el objeto User esperado cuando se busque por ID
        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // Llamar al método getUserById para probarlo
        Optional<User> result = userService.getUsersById(id);

        // Confirmar que el Optional devuelto contenga el objeto User esperado
        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get());
    }

    @Test
    public void testGetUserById_NotFound() {
        // Crear un ID de prueba de usuario que no existe
        Long id = 1L;

        // Configurar el comportamiento del mock del repositorio para que devuelva un Optional vacío cuando se busque por ID
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Llamar al método para probarlo
        Optional<User> result = userService.getUsersById(id);

        // Confirmar que el Optional devuelto esté vacío
        assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteUserById() {
        // Crear un ID de prueba de usuario y un objeto User esperado
        Long id = 1L;
        User expectedUser = new User();

        // Configurar el comportamiento del mock del repositorio para que devuelva el objeto User esperado cuando se busque por ID
        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // Llamar al método para probarlo
        User result = userService.deleteUserById(id);

        // Confirmar que el objeto User devuelto sea el mismo que el esperado
        assertEquals(expectedUser, result);

        // Confirmar que el método del repositorio se haya llamado una vez con el ID simulado
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteUserById_NotFound() {
        // Crear un ID de prueba de usuario que no existe
        Long id = 1L;

        // Configurar el comportamiento del mock del repositorio para que devuelva un Optional vacío cuando se busque por ID
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Llamar al método deleteUserById para probarlo
        assertThrows(NoSuchElementException.class, () -> userService.deleteUserById(id));
    }

    @Test
    public void testUpdateUser() {
        // Crear un ID de prueba de usuario, un objeto UserDto con datos actualizados y un objeto User existente
        Long id = 1L;
        UserDto userDto = new UserDto();
        User existingUser = new User();

        // Crear un User actualizado...
        User updatedUser = new User();

        // Configurar el comportamiento del mock del repositorio para que devuelva el objeto User existente cuando se busque por ID
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        // Configurar el comportamiento del mock del repositorio para que devuelva el objeto User actualizado cuando se guarde
        when(userRepository.save(any())).thenReturn(updatedUser);

        // Llamar al método updateUser para probarlo
        User result = userService.updateUser(id, userDto);

        // Confirmar que el User devuelto sea igual al User actualizado
        assertEquals(updatedUser, result);

    }
}