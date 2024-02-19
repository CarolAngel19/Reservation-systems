package Bookingd.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long id_glamping;
    private Long number_of_adults;
    private Long number_of_children;
    private Date date_entry;
    private Date date_exit;
    private String hour_entry;
    private String hour_exit;
}
