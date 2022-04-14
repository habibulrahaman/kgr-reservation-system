package com.kgr.reservationsystem.entity;

import com.kgr.reservationsystem.model.Agency;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "reservation_created_on")
    private Date reservationMade;

    @Column(name = "room_name")
    private String roomName;

    @Column(name ="breakfast")
    private boolean breakfast;

    @Column(name ="agency")
    @Enumerated(EnumType.STRING)
    private Agency agency;

    @Column(name ="check_in")
    private LocalDate checkIn;

    @Column(name ="check_out")
    private LocalDate checkOut;

    @Column(name ="nights")
    private int nights;

    @Column(name ="booking_amount")
    private BigDecimal bookingAmount;

    @Column(name ="deposit_amount")
    private BigDecimal deposit;

    @Column(name ="remaining_amount")
    private BigDecimal remainingAmount;

    @Column(name ="comments")
    private String comments;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_timestamp")
    private Date createdTimestamp;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_timestamp")
    private Date updatedTimestamp;

    @Version
    private int version;
}
