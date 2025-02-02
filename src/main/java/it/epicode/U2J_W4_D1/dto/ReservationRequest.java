package it.epicode.U2J_W4_D1.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class ReservationRequest {
    @NotNull(message = "the field 'trip' cannot be null")
    @FutureOrPresent(message="the date of the trip must be present or future")
    private LocalDate reservationDate;
    @NotNull(message = "the field 'trip' cannot be null")
    private Long tripId;
    private String annotation; //non inserita validazione perché facoltativa
    @NotNull(message = "the field 'employee' cannot be null")
    private Long employeeId;
}
