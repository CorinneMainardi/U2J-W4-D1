package it.epicode.U2J_W3_D5.dto;

import it.epicode.U2J_W3_D5.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TripRequest {
    @NotBlank(message = "the field 'destination' cannot be blank")
    private String destination;
    @NotNull(message = "the field 'trip' cannot be null")
    @FutureOrPresent(message="the date of the trip must be present or future")
    private LocalDate date;
    @NotNull(message = "the field 'status' cannot be null")
    private Status status;
}
