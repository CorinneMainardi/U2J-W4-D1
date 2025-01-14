package it.epicode.U2J_W4_D1.repositories;

import it.epicode.U2J_W4_D1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
