package it.epicode.U2J_W3_D5.repositories;

import it.epicode.U2J_W3_D5.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
