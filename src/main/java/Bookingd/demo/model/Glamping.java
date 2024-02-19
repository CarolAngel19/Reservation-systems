package Bookingd.demo.model;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "glamping")
public class Glamping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long capacity;
    private String description;
}
