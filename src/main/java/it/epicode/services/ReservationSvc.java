package it.epicode.U2J_W3_D5.services;


import it.epicode.U2J_W3_D5.dto.EmployeeRequest;
import it.epicode.U2J_W3_D5.dto.ReservationRequest;
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

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class ReservationSvc {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ReservationRepository reservationRepository;

   public ReservationSvc(ReservationRepository reservationRepository){
       this.reservationRepository = reservationRepository;
   }

 public Page<Reservation> findAllReservations(Pageable pageable){
    return reservationRepository.findAll(pageable);
 }

   public List <Reservation> getAllReservations(){
       return reservationRepository.findAll();
   }
   public Reservation getReservationById(Long id){
       if (!reservationRepository.existsById(id)){
           throw new EntityNotFoundException(("Reservation not found"));
       }
       return reservationRepository.findById(id).get();
   }
    @Transactional
    public Reservation createReservation(@Valid ReservationRequest reservationRequest) {

        boolean reservationExists = reservationRepository.existsByEmployeeIdAndReservationDate(
                reservationRequest.getEmployeeId(),
                reservationRequest.getReservationDate()
        );
        try {

            if (reservationExists) {
                throw new RuntimeException("Employee already has a reservation for this date");
            }


            Reservation r = new Reservation();
            Employee e = employeeRepository.findById(reservationRequest.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            Trip t = tripRepository.findById(reservationRequest.getTripId())
                    .orElseThrow(() -> new RuntimeException("Trip not found"));

            BeanUtils.copyProperties(reservationRequest, r);
            r.setEmployee(e);
            r.setTrip(t);

            return reservationRepository.save(r);
        }

        catch (
                UploadException ex) {

            throw new RuntimeException("Failed to create reservation due to upload issue: " + ex.getMessage(), ex);

        } catch (Exception ex) {

            throw new RuntimeException("An unexpected error occurred while creating the reservation: " + ex.getMessage(), ex);
        }
    }


    public Reservation updateReservation(Long id, @Valid ReservationRequest reservation){
       try {
           Reservation r = getReservationById(id);
           BeanUtils.copyProperties(reservation, r);
           return reservationRepository.save(r);
       }catch (UploadException ex) {
           throw new RuntimeException("Failed to update reservation  due to upload issue: " + ex.getMessage(), ex);

       } catch (Exception ex) {
           throw new RuntimeException("An unexpected error occurred while updating the reservation " + ex.getMessage(), ex);
       }
   }




    @Transactional
    public Reservation deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
        return reservation;
    }
}




