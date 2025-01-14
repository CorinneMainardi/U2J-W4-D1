package it.epicode.U2J_W4_D1.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate reservationDate;
    @ManyToOne
    private Trip trip;
    private String annotation;
    @ManyToOne
    private Employee employee;
}
