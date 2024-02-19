package Bookingd.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {
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
