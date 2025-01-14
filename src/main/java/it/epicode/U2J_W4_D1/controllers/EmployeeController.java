package it.epicode.U2J_W4_D1.controllers;

import it.epicode.U2J_W4_D1.dto.EmployeeImgRequest;
import it.epicode.U2J_W4_D1.services.CloudinarySvc;
import it.epicode.U2J_W4_D1.dto.EmployeeRequest;
import it.epicode.U2J_W4_D1.entities.Employee;
import it.epicode.U2J_W4_D1.services.EmployeeSvc;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeSvc employeeSvc;

    @Autowired
    private CloudinarySvc cloudinarySvc;

@GetMapping
    public ResponseEntity<Page<Employee>>findAllEmployees(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(employeeSvc.findAllEmployees(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Employee>findEmployeeById(@PathVariable Long id){
    return ResponseEntity.ok(employeeSvc.getEmployeeById(id));
    }
    @PostMapping
    public ResponseEntity<Employee>createEmployee(@RequestBody EmployeeRequest employeeRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeSvc.createEmployee(employeeRequest));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/upload/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Employee> uploadImg(
            @RequestPart("file") MultipartFile file,
            @PathVariable Long id) {

        String folder = "img";
        Map<String, Object> result = cloudinarySvc.uploader(file, folder);
        String imageUrl = (String) result.get("url");
        EmployeeImgRequest imgRequest = new EmployeeImgRequest();
        imgRequest.setImg(imageUrl);
        Employee updatedEmployee = employeeSvc.uploadImg(id, imgRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Employee>updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id ){
    return ResponseEntity.ok(employeeSvc.updateEmployee(id, employeeRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){

    return ResponseEntity.ok(employeeSvc.deleteEmployee(id));
    }

}





