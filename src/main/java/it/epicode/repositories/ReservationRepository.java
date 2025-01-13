package it.epicode.U2J_W3_D5.repositories;

import it.epicode.U2J_W3_D5.entities.Employee;
import it.epicode.U2J_W3_D5.entities.Reservation;
import it.epicode.U2J_W3_D5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteByTrip(Trip trip);
    boolean existsByEmployeeIdAndReservationDate(Long employeeId, LocalDate reservationDate);
    void deleteByEmployee(Employee employee);
}
