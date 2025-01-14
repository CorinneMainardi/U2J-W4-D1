package it.epicode.U2J_W4_D1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "the field 'username' cannot be blank")
    private String username;
    @NotBlank(message = "the field 'name' cannot be blank")
    private String name;
    @NotBlank(message = "the field 'lastname' cannot be blank")
    private String lastname;
    @NotBlank(message = "the field 'email'  cannot be blank")
    @Email(message = "The field 'email' must contain a valid email address")
    private String email;
 //manca l'immagine perché è facoltativa, c'è un dto apposito per l'immagine
}
