package it.epicode.U2J_W3_D5.entities;


import it.epicode.U2J_W3_D5.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
     private String destination;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;
}
