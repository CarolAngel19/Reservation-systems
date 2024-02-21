package Bookingd.demo.services;

import Bookingd.demo.dto.GlampingDto;
import Bookingd.demo.dto.UserDto;
import Bookingd.demo.model.Glamping;
import Bookingd.demo.model.User;
import Bookingd.demo.repository.IGlampingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlampingService {
    @Autowired
    private IGlampingRepository iGlampingRepository;

    public List<Glamping> getAllGlamping(){
        return iGlampingRepository.findAll();
    }

    public Glamping createGlamping(GlampingDto glampingDto){

        Glamping newGlamping = new Glamping();

        newGlamping.setName(glampingDto.getName());
        newGlamping.setCapacity(glampingDto.getCapacity());
        newGlamping.setDescription(glampingDto.getDescription());
        newGlamping.setImage(glampingDto.getImage());

        return iGlampingRepository.save(newGlamping);
    }

    public Optional<Glamping> getGlampingById(Long id){
        return iGlampingRepository.findById(id);
    }

    public Glamping deleteGlampingById(Long id) {
        Glamping glamping = getGlampingById(id).get();
        iGlampingRepository.deleteById(id);
        return glamping;
    }

    public Glamping updateGlamping(Long id, GlampingDto glampingDto){
        Glamping existinGlamping = iGlampingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Glamping not found"));

        existinGlamping.setName(glampingDto.getName());
        existinGlamping.setCapacity(glampingDto.getCapacity());
        existinGlamping.setDescription(glampingDto.getDescription());
        existinGlamping.setImage(glampingDto.getImage());

        return iGlampingRepository.save(existinGlamping);
    }


}
