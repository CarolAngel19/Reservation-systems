package Bookingd.demo.controller;

import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.User;
import Bookingd.demo.services.UserService;
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

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUser() {
        // Simular el comportamiento del servicio
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = userController.getAllUser();

        // Verificar el código de estado y el tamaño de la lista devuelta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<User> users = (List<User>) responseEntity.getBody();
        assertEquals(2, users.size());
    }

    @Test
    void testCreateUser() {
        // Crear un objeto de UserDto simulado
        UserDto userDto = new UserDto();

        // Simular el comportamiento del servicio
        when(userService.createUser(any(UserDto.class))).thenReturn(new User());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = userController.createUser(userDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserById() {
        // Simular el comportamiento del servicio
        when(userService.getUsersById(anyLong())).thenReturn(Optional.of(new User()));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = userController.getAllUserById(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteUser() {
        // Simular el comportamiento del servicio
        when(userService.deleteUserById(anyLong())).thenReturn(new User());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = userController.deleteUser(1L);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateUser() {
        // Crear un objeto de UserDto simulado
        UserDto userDto = new UserDto();

        // Simular el comportamiento del servicio
        when(userService.updateUser(anyLong(), any(UserDto.class))).thenReturn(new User());

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = userController.updateUser(1L, userDto);

        // Verificar el código de estado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}