package it.epicode.U2J_W3_D5.services;

import it.epicode.U2J_W3_D5.dto.EmployeeImgRequest;
import it.epicode.U2J_W3_D5.dto.EmployeeRequest;
import it.epicode.U2J_W3_D5.entities.Employee;
import it.epicode.U2J_W3_D5.entities.Trip;
import it.epicode.U2J_W3_D5.exceptions.AlreadyExistsException;
import it.epicode.U2J_W3_D5.exceptions.UploadException;
import it.epicode.U2J_W3_D5.repositories.EmployeeRepository;
import it.epicode.U2J_W3_D5.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated

public class EmployeeSvc {
@Autowired
    EmployeeRepository employeeRepository;
@Autowired
    ReservationRepository reservationRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Page<Employee> findAllEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }
    public Employee getEmployeeById(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee not found");
        }
        return employeeRepository.findById(id).get();
    }
    public Employee createEmployee(@Valid EmployeeRequest employeeRequest) {
        try {

            if (employeeRepository.existsByUsername(employeeRequest.getUsername())) {
                throw new AlreadyExistsException("Duplicate employee. Username already exists.");
            }

            if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
                throw new AlreadyExistsException("Duplicate employee. Email already exists.");
            }

            Employee e = new Employee();
            BeanUtils.copyProperties(employeeRequest, e);
            return employeeRepository.save(e);

        } catch (UploadException ex) {

            throw new RuntimeException("Failed to create employee due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {

            throw new RuntimeException("An unexpected error occurred while creating the employee: " + ex.getMessage(), ex);
        }
    }


    public Employee updateEmployee( Long id,@Valid EmployeeRequest employeeRequest) {
        try {
            Employee e = getEmployeeById(id);
            if (!e.getUsername().equals(employeeRequest.getUsername()) &&
                    employeeRepository.existsByUsername(employeeRequest.getUsername())) {
                throw new AlreadyExistsException("Duplicate employee. Username already exists.");            }

            if (!e.getEmail().equals(employeeRequest.getEmail()) &&
                    employeeRepository.existsByEmail(employeeRequest.getEmail())) {
                throw new AlreadyExistsException("Duplicate employee. Email already exists.");
            }
            BeanUtils.copyProperties(employeeRequest, e, "id", "img");

            return employeeRepository.save(e);

        } catch (UploadException ex) {
            throw new RuntimeException("Failed to update employee due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the employee: " + ex.getMessage(), ex);
        }
    }



    @Transactional
    public Employee uploadImg(Long id, @Valid EmployeeImgRequest imgRequest) {
        // Recupera il dipendente dal repository
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Aggiorna il campo immagine
        e.setImg(imgRequest.getImg());

        // Salva e restituisce il dipendente aggiornato
        return employeeRepository.save(e);
    }



    @Transactional
    public Employee deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        reservationRepository.deleteByEmployee(employee);

        employeeRepository.delete(employee);
        return employee;
    }


}

