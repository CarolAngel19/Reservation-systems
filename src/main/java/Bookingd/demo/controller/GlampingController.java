package Bookingd.demo.controller;


import Bookingd.demo.dto.GlampingDto;
import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.Glamping;
import Bookingd.demo.model.User;
import Bookingd.demo.services.GlampingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/glamping/")
public class GlampingController {
    @Autowired
    private GlampingService glampingService;

    @GetMapping("all")
    public ResponseEntity<?> getAllGlamping(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(glampingService.getAllGlamping());
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createGlamping(@RequestBody GlampingDto glampingDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(glampingService.createGlamping(glampingDto));
        }catch (Exception e){
            System.out.println("Error create users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllGlampingById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(glampingService.getGlampingById(id));
        }catch (Exception e){
            System.out.println("Error get all users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGlamping(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(glampingService.deleteGlampingById(id));
        }catch (Exception e){
            System.out.println("Error create users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateGlamping(@PathVariable Long id, @RequestBody GlampingDto glampingDto){
        try {
            Glamping updatedGlamping = glampingService.updateGlamping(id, glampingDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedGlamping);
        } catch (Exception e){
            System.out.println("Error updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
