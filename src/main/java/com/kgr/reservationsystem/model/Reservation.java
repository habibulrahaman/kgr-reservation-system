package com.kgr.reservationsystem.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {
    long id;
    String guestName;
    Date bookingMade;
    String roomName;
    boolean breakfast;
    Agency agency;
    LocalDate checkIn;
    LocalDate checkOut;
    int nights;
    BigDecimal bookingAmount;
    BigDecimal deposit;
    BigDecimal remainingAmount;
    String comments;
    Date createdTimestamp;
    Date updatedTimestamp;
    int version;
}
