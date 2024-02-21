package Bookingd.demo.repository;

import Bookingd.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE name = :name LIMIT 1;", nativeQuery = true)
    Optional<User> getByUsename(@Param("name") String name);
}
