package it.epicode.U2J_W3_D5.controllers;

import it.epicode.U2J_W3_D5.services.CloudinarySvc;
import it.epicode.U2J_W3_D5.dto.ReservationRequest;
import it.epicode.U2J_W3_D5.entities.Reservation;
import it.epicode.U2J_W3_D5.services.ReservationSvc;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationSvc reservationSvc;

    @Autowired
    private CloudinarySvc cloudinarySvc;
    @GetMapping

    public ResponseEntity<Page<Reservation>> findAllReservations(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(reservationSvc.findAllReservations(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation>findReservationById(@PathVariable Long id){
        return ResponseEntity.ok(reservationSvc.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation>createReservation(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationSvc.createReservation(reservationRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reservation>updateTrip(@RequestBody ReservationRequest reservationRequest, @PathVariable Long id ){
        return ResponseEntity.ok(reservationSvc.updateReservation(id, reservationRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationSvc.deleteReservation(id));
    }
}





