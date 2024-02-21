package Bookingd.demo.controller;


import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.User;
import Bookingd.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/user/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public ResponseEntity<?> getAllUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllUserById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersById(id));
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByName(name));
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userDto));
        }catch (Exception e){
            System.out.println("Error create users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(id));
        }catch (Exception e){
            System.out.println("Error create users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserDto userDto){
        try {
            User updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (Exception e){
            System.out.println("Error updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
