package it.epicode.U2J_W4_D1.repositories;

import it.epicode.U2J_W4_D1.entities.Employee;
import it.epicode.U2J_W4_D1.entities.Reservation;
import it.epicode.U2J_W4_D1.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteByTrip(Trip trip);
    boolean existsByEmployeeIdAndReservationDate(Long employeeId, LocalDate reservationDate);
    void deleteByEmployee(Employee employee);
}
