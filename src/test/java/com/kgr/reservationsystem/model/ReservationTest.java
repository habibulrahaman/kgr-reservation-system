package com.kgr.reservationsystem.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservationTest {

    @Test
    void shouldCreateReservation() {
        String guestName = "Mohammed Gazi";
        Reservation reservation = Reservation.builder().guestName("Mohammed Gazi").version(1).build();
        assertThat(reservation.getGuestName()).isEqualTo(guestName);
    }

}