package com.kgr.reservationsystem.repository;

import com.kgr.reservationsystem.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "from ReservationEntity r where :date BETWEEN r.checkIn AND r.checkOut")
    List<ReservationEntity> getAllBetweenDates(@Param("date") LocalDate date);

    List<ReservationEntity> findAllByCheckInAfter(LocalDate now);

    @Query(value = "from ReservationEntity r where r.checkIn BETWEEN :startDate AND :endDate")
    List<ReservationEntity> getAllByMonth(@Param("startDate") final LocalDate startDate, @Param("endDate") final LocalDate endDate);
}
