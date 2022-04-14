package com.kgr.reservationsystem.service;

import com.kgr.reservationsystem.entity.ReservationEntity;
import com.kgr.reservationsystem.mapper.ReservationMapper;
import com.kgr.reservationsystem.model.Reservation;
import com.kgr.reservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    public Reservation saveReservation(Reservation reservation) {

        reservation = reservation.toBuilder()
                .nights((int) DAYS.between(reservation.getCheckIn(), reservation.getCheckOut()))
                .remainingAmount(reservation.getBookingAmount().subtract(reservation.getDeposit()))
                .build();

        final ReservationEntity reservationEntity = reservationRepository.save(reservationMapper.domainToEntity(reservation));

        return reservationMapper.entityToDomain(reservationEntity);
    }

    public List<Reservation> getReservations() {
        return reservationMapper.entitiesToDomains(reservationRepository.findAll());
    }

    public Reservation getReservation(long reservationId) {
        return reservationMapper.entityToDomain(reservationRepository.getById(reservationId));
    }
}
