package it.epicode.U2J_W3_D5.repositories;

import it.epicode.U2J_W3_D5.entities.Employee;
import it.epicode.U2J_W3_D5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
