package Bookingd.demo.repository;
import Bookingd.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
}