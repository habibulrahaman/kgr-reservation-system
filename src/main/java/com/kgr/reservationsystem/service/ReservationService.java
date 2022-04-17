package com.kgr.reservationsystem.service;

import com.kgr.reservationsystem.entity.ReservationEntity;
import com.kgr.reservationsystem.mapper.ReservationMapper;
import com.kgr.reservationsystem.model.Reservation;
import com.kgr.reservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    public List<Reservation> getTodayReservations() {
        return reservationMapper.entitiesToDomains(
                reservationRepository.getAllBetweenDates(LocalDate.now()));
    }

    public List<Reservation> getFutureReservations() {
        return reservationMapper.entitiesToDomains(
                reservationRepository.findAllByCheckInAfter(LocalDate.now()));
    }

    public List<Reservation> getReservationsByMonth(int month) {
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), month, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), month, startDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth());
        System.out.println(startDate);
        System.out.println(endDate);
        return reservationMapper.entitiesToDomains(
                reservationRepository.getAllByMonth(startDate, endDate));
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        return reservationMapper.entitiesToDomains(
                reservationRepository.getAllBetweenDates(date));
    }

    public Reservation getReservation(long reservationId) {
        return reservationMapper.entityToDomain(reservationRepository.getById(reservationId));
    }

    public void deleteReservation(long reservationId) {
        ReservationEntity reservationEntity = reservationRepository.getById(reservationId);
        reservationRepository.deleteById(reservationEntity.getId());
    }

    public Reservation updateReservation(Reservation updatedReservation) {
        ReservationEntity reservationEntity = reservationRepository.getById(updatedReservation.getId());

        ReservationEntity entity = reservationEntity.toBuilder()
            .guestName(updatedReservation.getGuestName())
            .roomName(updatedReservation.getRoomName())
            .breakfast(updatedReservation.isBreakfast())
            .agency(updatedReservation.getAgency())
            .checkIn(updatedReservation.getCheckIn())
            .checkOut(updatedReservation.getCheckOut())
            .bookingAmount(updatedReservation.getBookingAmount())
            .deposit(updatedReservation.getDeposit())
            .comments(updatedReservation.getComments())
            .nights((int) DAYS.between(updatedReservation.getCheckIn(), updatedReservation.getCheckOut()))
            .remainingAmount(updatedReservation.getBookingAmount().subtract(updatedReservation.getDeposit()))
            .build();

        return reservationMapper.entityToDomain(reservationRepository.save(entity));
    }
}
