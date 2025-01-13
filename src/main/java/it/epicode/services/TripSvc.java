package it.epicode.U2J_W3_D5.services;

import it.epicode.U2J_W3_D5.dto.EmployeeRequest;
import it.epicode.U2J_W3_D5.dto.TripRequest;
import it.epicode.U2J_W3_D5.entities.Employee;
import it.epicode.U2J_W3_D5.entities.Reservation;
import it.epicode.U2J_W3_D5.entities.Trip;
import it.epicode.U2J_W3_D5.enums.Status;
import it.epicode.U2J_W3_D5.exceptions.AlreadyExistsException;
import it.epicode.U2J_W3_D5.exceptions.UploadException;
import it.epicode.U2J_W3_D5.repositories.EmployeeRepository;
import it.epicode.U2J_W3_D5.repositories.ReservationRepository;
import it.epicode.U2J_W3_D5.repositories.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

public class TripSvc {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    ReservationRepository reservationRepository;
    public TripSvc(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }
    public List <Trip> getAllTrips(){
        return tripRepository.findAll();
    }
    public Page<Trip> findAllTrips(Pageable pageable){
        return tripRepository.findAll(pageable);
    }
    public Trip getTripById( Long id){
        if(!tripRepository.existsById(id)){
            throw new EntityNotFoundException(("trip not found"));
        }
        return tripRepository.findById(id).get();
    }
    public Trip createTrip(@Valid TripRequest tripRequest){
        try {
            Trip t = new Trip();
            BeanUtils.copyProperties(tripRequest, t);

            return tripRepository.save(t);
        }
        catch (
                UploadException ex) {

            throw new RuntimeException("Failed to create trip due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {

            throw new RuntimeException("An unexpected error occurred while creating the trip: " + ex.getMessage(), ex);
        }
    }


    public Trip updateTrip(Long id,@Valid TripRequest tripRequest){
        try {
            Trip t = getTripById(id);
            BeanUtils.copyProperties(tripRequest, t);
            return tripRepository.save(t);
        }
        catch (UploadException ex) {
            throw new RuntimeException("Failed to update trip due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the trip: " + ex.getMessage(), ex);
        }
    }





    @Transactional
    public Trip updateTripStatus(Long id, Status status) {
        try {
            Trip trip = getTripById(id);
            trip.setStatus(status);
            return tripRepository.save(trip);
        } catch (UploadException ex) {
            throw new RuntimeException("Failed to update trip status due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the trip status " + ex.getMessage(), ex);
        }
    }


    @Transactional
    public Trip deleteTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // Elimina le prenotazioni associate
        reservationRepository.deleteByTrip(trip);

        // Elimina il viaggio
        tripRepository.delete(trip);
        return trip;
    }
}








