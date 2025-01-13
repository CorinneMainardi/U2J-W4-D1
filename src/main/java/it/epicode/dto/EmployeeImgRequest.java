package it.epicode.U2J_W3_D5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeImgRequest {
    @NotNull(message = "The field 'img' cannot be null")
    private String img;
}
