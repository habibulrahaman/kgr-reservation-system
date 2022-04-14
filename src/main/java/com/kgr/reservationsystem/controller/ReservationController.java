package com.kgr.reservationsystem.controller;

import com.kgr.reservationsystem.model.Reservation;
import com.kgr.reservationsystem.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable(name = "reservationId") long reservationId) {
        return ResponseEntity.ok(reservationService.getReservation(reservationId));
    }
}
