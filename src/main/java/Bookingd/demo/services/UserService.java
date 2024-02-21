package Bookingd.demo.services;

import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.User;
import Bookingd.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public List<User> getAllUsers(){
        return iUserRepository.findAll();
    }

    public User createUser(UserDto userDto){
        User newUser = new User();

        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());

        return iUserRepository.save(newUser);
    }

    public Optional<User> getUsersById(Long id){
        return iUserRepository.findById(id);
    }

    public Optional<User> getUsersByName(String name){
        return iUserRepository.getByUsename(name);
    }

    public User deleteUserById(Long id) {
        User user = getUsersById(id).get();
        iUserRepository.deleteById(id);
        return user;
    }

    public User updateUser(Long id, UserDto userDto){
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        return iUserRepository.save(existingUser);
    }

}
