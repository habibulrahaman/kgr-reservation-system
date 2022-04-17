package com.kgr.reservationsystem.controller;

import com.kgr.reservationsystem.model.Reservation;
import com.kgr.reservationsystem.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@Slf4j
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> create(@RequestBody final Reservation reservation) {
        log.info("Received reservation - {}", reservation);
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.getTodayReservations());
    }

    @GetMapping("/future")
    public ResponseEntity<List<Reservation>> getFutureReservations() {
        return ResponseEntity.ok(reservationService.getFutureReservations());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable(name = "date") final String date) {
        return ResponseEntity.ok(reservationService.getReservationsByDate(LocalDate.parse(date)));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<Reservation>> getReservationsByMonth(@PathVariable(name = "month") final int month) {
        return ResponseEntity.ok(reservationService.getReservationsByMonth(month));
    }

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable(name = "reservationId") long reservationId) {
        return ResponseEntity.ok(reservationService.getReservation(reservationId));
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable(name = "reservationId") long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody final Reservation updatedReservation) {
        return ResponseEntity.ok(reservationService.updateReservation(updatedReservation));
    }
}
