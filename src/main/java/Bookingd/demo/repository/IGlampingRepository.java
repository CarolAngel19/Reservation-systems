package Bookingd.demo.repository;

import Bookingd.demo.model.Glamping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGlampingRepository extends JpaRepository<Glamping, Long> {
}
