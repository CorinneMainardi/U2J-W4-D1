package it.epicode.U2J_W4_D1.entities;


import it.epicode.U2J_W4_D1.enums.Status;
import jakarta.persistence.*;
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
