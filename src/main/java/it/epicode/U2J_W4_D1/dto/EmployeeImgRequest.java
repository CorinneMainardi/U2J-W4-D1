package it.epicode.U2J_W4_D1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeImgRequest {
    @NotNull(message = "The field 'img' cannot be null")
    private String img;
}
