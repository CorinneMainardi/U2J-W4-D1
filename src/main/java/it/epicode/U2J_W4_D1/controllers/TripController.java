package it.epicode.U2J_W4_D1.controllers;

import it.epicode.U2J_W4_D1.dto.TripStatusRequest;
import it.epicode.U2J_W4_D1.services.CloudinarySvc;
import it.epicode.U2J_W4_D1.dto.TripRequest;
import it.epicode.U2J_W4_D1.entities.Trip;
import it.epicode.U2J_W4_D1.services.TripSvc;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    @Autowired
    private TripSvc tripSvc;

    @Autowired
    private CloudinarySvc cloudinarySvc;
    @GetMapping

    public ResponseEntity<Page<Trip>> findAllTrips(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(tripSvc.findAllTrips(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trip>findTripById(@PathVariable Long id){
        return ResponseEntity.ok(tripSvc.getTripById(id));
    }
    @PostMapping
    public ResponseEntity<Trip>createTrip(@RequestBody TripRequest tripRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(tripSvc.createTrip(tripRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Trip>updateTrip(@RequestBody TripRequest tripRequest, @PathVariable Long id ){
        return ResponseEntity.ok(tripSvc.updateTrip(id, tripRequest));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Trip>updateTripStatus(@RequestBody TripStatusRequest tripStatusRequest, @PathVariable Long id){
        return ResponseEntity.ok(tripSvc.updateTripStatus(id, tripStatusRequest.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable Long id){
        return ResponseEntity.ok(tripSvc.deleteTrip(id));
    }
}









