package it.epicode.U2J_W4_D1.dto;

import it.epicode.U2J_W4_D1.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TripStatusRequest {
    @NotNull(message = "the field 'status' cannot be null")
    private Status status;
}
