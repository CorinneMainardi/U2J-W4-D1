package it.epicode.U2J_W4_D1.auth.auth;


import it.epicode.U2J_W4_D1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser>findByEmployee(Employee employee);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
